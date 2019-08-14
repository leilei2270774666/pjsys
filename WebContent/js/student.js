layui.use(['layer', 'form', 'laydate', 'laypage', 'element'], function () {
  var layer = layui.layer,
    form = layui.form,
    laydate = layui.laydate,
    laypage = layui.laypage,
    element = layui.element;
  // 获取当前登录信息
  var user_id = $.cookie('users_id');
  // 登录校验
  if (user_id === undefined || user_id == '') {
    window.location.href = "index.html";
  }
  // 退出登录
  $('.del-id').click(function () {
    $.cookie('users_id', '');
    window.location.reload();
  })
  $('#user_id').val(user_id);
  $('#family-tel1').val($.cookie('users_tel'))
  // 二级跳转及其背景色
  for (var i = 0; i < $('.aside-ul').children().length; i++) {
    $('.aside-ul').children().eq(i).on('click', function () {
      $(this).children('dl').toggle(300);
      $(this).addClass('ul-bg').children('dl').addClass('dd-bg');
      $(this).siblings().removeClass('ul-bg');
      if ($(this).children('img').attr('src') == './img/arrow.png') {
        $(this).children('img').attr('src', './img/arrow2.png')
      } else {
        $(this).children('img').attr('src', './img/arrow.png')
      }
    })
    // 二级菜单背景色
    $('.aside-ul').children().eq(i).children('dl').children('dd').on('click', function () {
      // $('.aside-ul').children().eq(i).on('click','dl dd',function(){
      $(this).addClass('dl-bg').siblings().removeClass('dl-bg');
      $(this).parent().parent().siblings().children().children().removeClass('dl-bg');
      $(this).parent().parent().addClass('ul-bg').siblings().removeClass('ul-bg')
    })
  }
  $.post('/pjsys/firstOnePicture',function(data){
    console.log(data)
    console.log(data.picturepath)
    img= '<img  style="width: 100%;" src="'+data.picturepath+'" alt="学习日历">'
    $('.pic').html(img)
  })
//  ===================学生信息开始===============
  /**
   * 学生信息
   */
  function stuInfo() {
    $.post('pjsys/findById.action', {
      user_id: user_id
    }, function (data) {
      var frxx_form_hmtl = '';
      for (var i = 0; i < data.length; i++) {
        var sex;
        if (data[i].sex === 1) {
          sex = '男'
        } else {
          sex = '女'
        }
        frxx_form_hmtl += '<tr class="r-tbody-tr" xs-id="' + data[i].id + '"><td>' + data[i].stuName + '</td><td>' + sex + '</td><td>' + data[i].birthday + '</td><td>' + data[i].personalNum + '</td><td>' + data[i].address + '</td><td>' + data[i].parent + '</td><td>' + data[i].mobilephone + '</td><td><span id="xs_update" style="cursor: pointer;" data-index="' + data[i].id + '">修改</span><span id="xs_delete" style="cursor: pointer;" data-index="' + data[i].id + '">删除</span></td></tr>';
      }
      $('#frxx_form').html(frxx_form_hmtl)
    })
  }
  // ------------------学生信息------学生删除
  $('body').on('click', '#xs_delete', function () {
    var index = $(this).attr('data-index');
    $.post('pjsys/delete.action', {
      id: index
    }, function (data) {
      if (data === 1) {
        layer.msg('删除成功', {
          time: 1000
        }, function () {
          $('.a1').click();
        })
      } else {
        layer.msg('删除失败')
      }
    })
  })
  // ================学生信息修改
  // =======显示修改框
  $('body').on('click', '#frxx_form .information-xg', function () {
    $('.xg-fade-student').show()
  })
  //========取消修改
  $('body').on('click', '.xg-fade-student .xg-student-btn-del', function () {
    $('.xg-fade-student').hide()
  })
  // ------------------学生信息------学生修改
  $('body').on('click', '#xs_update', function () {
    $('.xg-fade-student').fadeIn()
    $.post('pjsys/findById1.action', {
      id: $(this).attr('data-index')
    }, function (data) {
      $('#xg-student').val(data[0].stuName)
      // console.log(data);
      if (data[0].sex === 1) {
        $('.xg-fade-student .fade-student-center input[value="1"]').click()
      }
      if (data[0].sex === 2) {
        $('.xg-fade-student .fade-student-center input[value="2"]').click()
      }
      $('#xgxsid').val(data[0].id)
      $('#xg-sfzh').val(data[0].personalNum)
      $('#xg-zdxx').val(data[0].schoolname)
      $('#xg-gzdw').val(data[0].workaddress)
      $('#xg-address').val(data[0].address)
      $('#xg-yb').val(data[0].postcode)
      $('#xg-family-name').val(data[0].parent)
      $('#xg-family-tel').val(data[0].mobilephone)
      $('#xg-family-name1').val(data[0].emergencyContactName)
      $('#xg-family-tel1').val(data[0].emergencyContactPhone)
    })
  })
  //----------------学生信息--------学生修改提交
  $('#stuName').blur(function () {
    if ($('#add_xsform #stuName').val() == '') {
      layer.msg('学生名字不能为空');
      return false;
    }
  })
  $('.sfzh').blur(function () {
    if ($('#add_xsform .sfzh').val() == '') {
      layer.msg('请输入身份证号');
      return false;
    }
  })
  $('.school').blur(function () {
    if ($('#add_xsform .school').val() == '') {
      layer.msg('请输入在读学校');
      return false;
    }
  })
  $('.jtzz').blur(function () {
    if ($('#add_xsform .jtzz').val() == '') {
      layer.msg('请输入家庭住址');
      return false;
    }
  })
  $('.yb').blur(function () {
    if ($('#add_xsform .yb').val() == '') {
      layer.msg('请输入邮编');
      return false;
    }
  })
  $('.jzxm1').blur(function () {
    if ($('#add_xsform .jzxm1').val() == '') {
      layer.msg('请输入家长姓名');
      return false;
    }
  })
  $('.tel1').blur(function () {
    if ($('#add_xsform .tel').val() == '') {
      layer.msg('请输入家长手机号码');
      return false;
    }
  })
  $('.jtxm').blur(function () {
    if ($('#add_xsform .jtzz').val() == '') {
      layer.msg('请输入备用联系人姓名');
      return false;
    }
  })
  $('.tel').blur(function () {
    if ($('#add_xsform .tel').val() == '') {
      layer.msg('请输入备用联系人手机号码');
      return false;
    }
  })
  //========确认修改
  $('.xg-student-btn').on('click', function () {
    var id = $('#xgxsid').val();
    var stuName = $('#xg-student').val();
    var sex = $('.xg-fade-student .fade-student-center input[name=sex]:checked').val()
    var personalNum = $('#xg-sfzh').val()
    var address = $('#xg-address').val()
    var postcode = $('#xg-yb').val()
    var parent = $('#xg-family-name').val()
    var mobilephone = $('#xg-family-tel').val()
    var EmergencyContactName = $('#xg-family-name1').val()
    var EmergencyContactPhone = $('#xg-family-tel1').val()
    var schoolname = $('#xg-zdxx').val()
    var workaddress = $('#xg-gzdw').val()
    var user_id = $.cookie('users_id');
    var reg = /(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/;
    if (reg.test(personalNum) === false) {
      layer.msg("身份证输入不合法");
      return false;
    }
    if (!($('#xg-family-name1').val().replace(/[^\u4E00-\u9FA5]/g, ''))) {
      layer.msg('备用联系人名字只能为中文');
      return false;
    }
    if (!(/^1[0-9]{10}$/.test($('#xg-family-tel1').val()))) {
      layer.msg('备用联系人手机号码格式错误');
      return false;
    }
    if (!($('#xg-family-name').val().replace(/[^\u4E00-\u9FA5]/g, ''))) {
      layer.msg('家长名字只能为中文');
      return false;
    }
    if (!(/^1[0-9]{10}$/.test($('#xg-family-tel').val()))) {
      layer.msg('家长手机号码格式错误');
      return false;
    }
    $.post('pjsys/update.action', {
      user_id: user_id,
      id: id,
      stuName: stuName,
      sex: sex,
      personalNum: personalNum,
      address: address,
      postcode: postcode,
      parent: parent,
      mobilephone: mobilephone,
      emergencyContactName: EmergencyContactName,
      emergencyContactPhone: EmergencyContactPhone,
      schoolname: schoolname,
      workaddress: workaddress
    }, function (data) {

      if (data === 1) {
        layer.msg('修改成功', {
          time: 1000
        }, function () {
          $('.xg-fade-student').hide()
          // window.location.reload()
          $('.a1').click();
        })
      } else {
        layer.msg('修改失败')
      }
    })
  })
  // -----------添加学生
  $('#add_xs_btn').click(function () {
    $("#add_xs_btn").fadeOut();
    add_xs()
  });
  // ------------添加学生----学生添加
  function add_xs() {
    if ($('#add_xsform #stuName').val() == '') {
      layer.msg('学生名字不能为空');
      $("#add_xs_btn").fadeIn();
      return false;
    }
    if (!($('#add_xsform #stuName').val().replace(/[^\u4E00-\u9FA5]/g, ''))) {
      layer.msg('学生名字只能为中文');
      $("#add_xs_btn").fadeIn();
      return false;
    }
    var reg = /(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/;
    if (reg.test($('#add_xsform  .sfzh ').val()) === false) {
      layer.msg("身份证输入不合法");
      $("#add_xs_btn").fadeIn();
      return false;
    }
    if ($('#add_xsform  .sfzh ').val() == '') {
      layer.msg('身份证号码不能为空');
      $("#add_xs_btn").fadeIn();
      return false;
    }
    if ($('#add_xsform  .jtzz ').val() == '') {
      layer.msg('请输入家庭地址');
      $("#add_xs_btn").fadeIn();
      return false;
    }

    if ($('#add_xsform .school').val() == '') {
      layer.msg('请输入在读学校');
      $("#add_xs_btn").fadeIn();
      return false;
    }
    if ($('#add_xsform  .yb ').val() == '') {
      layer.msg('请输入邮编');
      $("#add_xs_btn").fadeIn();
      return false;
    }
    if ($('#add_xsform  .jzxm1 ').val() == '') {
      layer.msg('请输入家长姓名');
      $("#add_xs_btn").fadeIn();
      return false;
    }
    if (!($('#add_xsform  .jzxm1 ').val().replace(/[^\u4E00-\u9FA5]/g, ''))) {
      layer.msg('家长姓名只能为中文');
      $("#add_xs_btn").fadeIn();
      return false;
    }
    if ($('#add_xsform  .tel1 ').val() == '') {
      layer.msg('请输入家长手机号');
      $("#add_xs_btn").fadeIn();
      return false;
    }
    if (!(/^1[0-9]{10}$/.test($('#add_xsform  .tel1 ').val()))) {
      layer.msg('家长手机号码格式错误');
      $("#add_xs_btn").fadeIn();
      return false;
    }
    if (!($('#add_xsform  .jzxm ').val().replace(/[^\u4E00-\u9FA5]/g, ''))) {
      layer.msg('备用联系人姓名只能为中文');
      $("#add_xs_btn").fadeIn();
      return false;
    }
    if ($('#add_xsform  .jzxm ').val() == '') {
      layer.msg('请输入备用联系人姓名');
      $("#add_xs_btn").fadeIn();
      return false;
    }
    if ($('#add_xsform  .tel ').val() == '') {
      layer.msg('请输入备用联系人电话号码');
      $("#add_xs_btn").fadeIn();
      return false;
    }
    if (!(/^1[0-9]{10}$/.test($('#add_xsform  .tel ').val()))) {
      layer.msg('备用联系人手机号错误');
      $("#add_xs_btn").fadeIn();
      return false;
    }
    $.ajax({
      //几个参数需要注意一下
      type: "POST", //方法类型
      dataType: "json", //预期服务器返回的数据类型
      url: "pjsys/add.action", //url
      data: $('#add_xsform').serialize(),
      success: function (msg) {
        if (msg !== 0) {
          layer.msg('添加学生成功', { time: 2000 }, function () {
            $('.a1').click()
            $("#add_xs_btn").fadeIn();
          });
        } else {
          layer.msg('身份证重复添加学生失败');
          $("#add_xs_btn").fadeIn();
        }
      },
    });
  };
  //  ===================学生信息结束===============
  // =====================课程选择开始==============
  /**
   * 
   * 课程选择------所有学生
   */
  function selStudent() {
    $.post('pjsys/findById.action', {
      user_id: user_id
    }, function (data) {
      var select = '<option value=""></option>';
      for (var i = 0; i < data.length; i++) {
        if (i == 0) {
          select += '<option value="' + data[i].id + '" selected >' + data[i].stuName + '</option>'
        } else {
          select += '<option value="' + data[i].id + '">' + data[i].stuName + '</option>'
        }
      }
      $('#add_xk select[name="interest"]').html(select);
      form.render('select'); //这个很重要
    })
  }
  /**
   * major课程选择下--------学科分类
   */
  function courseMajor() {
    $.post('/pjsys/findAllMajors.action', function (data) {
      var frxx_form_hmtl = '';
      for (var i = 0; i < data.length; i++) {
        if (i == 0) {
          frxx_form_hmtl += '<span class="xk-bgs" data-id="' + data[i].id + '" >' + data[i].major + '</span>'
        } else {
          frxx_form_hmtl += '<span data-id="' + data[i].id + '" >' + data[i].major + '</span>'
        }
      }
      $('#add_xk .subject-all').html(frxx_form_hmtl);
      $('#add_xk .subject-all span').eq(0).click()
    })
  }
  // 课程选择-----选课操作
  $('body').on('click', '#add_xk .subject-all span', function () {
    var stu_id = $('#add_xk select[name="interest"]').val(); //学生id
    var major_id = $(this).attr('data-id'); //专业id
    var page = 1;
    $('#add_xk .r-table .r-tbody').html('<tr class="r-tbody-tr"><td colspan="7">当前学生不符合本科目招生条件</td></tr>');
    $.post('pjsys/findBySubjectAndAge.action', {stu_id: stu_id,major_id: major_id,page: 1}, function (data) {
      page = data.total;
      
      laypage.render({
        elem: 'student-subject'
        ,count: page
        ,theme: "#65daf7"
        ,jump: function (obj, first) {
          $.post('pjsys/findBySubjectAndAge.action', {stu_id: stu_id,major_id: major_id,page: obj.curr}, function (data) {
            var html;
            if (data.datas == null) {
              $('#add_xk .r-table .r-tbody').html('<tr class="r-tbody-tr"><td colspan="8">当前学生不符合本科目招生条件。</td></tr>');
              return false;
            } else {
              for (var i = 0; i < data.datas.length; i++) {
                /*
                可以选课的前提
                1. 教务端开放选课 kaifang:1开放
                2. 剩余人数 num>0
                3. subject.interview ===1 需要面试
                4. infoFlag === 0 未选过该门课
                */
                if (data.datas[i].kaifang == 1 && data.datas[i].subject.interview == 1 && data.datas[i].infoFlag == 0 && data.datas[i].num>0) {
                  hl = '<span class="xg-bg subject-xz" id="xk-tj" style="cursor: pointer;" classinfo-id="' + data.datas[i].id + '" data-num="' + data.datas[i].num + '"data-subject="' + data.datas[i].subject.interview + '">选课</span><span class="xg-bg interview-xk" style="cursor: pointer;margin-left:4px">预约面试</span>'
                } else if (data.datas[i].kaifang == 1 && data.datas[i].subject.interview == 0 && data.datas[i].infoFlag == 0 && data.datas[i].num>0) {
                  hl = '<span class="xg-bg subject-xz" id="xk-tj" style="cursor: pointer;" classinfo-id="' + data.datas[i].id + '" data-num="' + data.datas[i].num + '"data-subject="' + data.datas[i].subject.interview + '">选课</span>'
                }
                else {
                  hl = '<span class="xg-bg" id="xk-tj" style=" background: #ccc;border:1px solid #ccc;" classinfo-id="' + data.datas[i].id + '" data-num="' + data.datas[i].num + '">选课</span>'
                }
                var day = '';
                if (data.datas[i].classroominfo.day === 1) {
                  day = '星期一'
                } else if (data.datas[i].classroominfo.day === 2) {
                  day = '星期二'
                } else if (data.datas[i].classroominfo.day === 3) {
                  day = '星期三'
                } else if (data.datas[i].classroominfo.day === 4) {
                  day = '星期四'
                } else if (data.datas[i].classroominfo.day === 5) {
                  day = '星期五'
                } else if (data.datas[i].classroominfo.day === 6) {
                  day = '星期六'
                } else if (data.datas[i].classroominfo.day === 7) {
                  day = '星期日'
                }
                // 适合年龄
                var now = new Date(data.datas[i].min_age);
                var year = now.getFullYear() - 1
                var mouth = now.getMonth() + 1
                var date = now.getDate()
                html += '<tr class="r-tbody-tr">\n' +
                  '                            <td><span>' + data.datas[i].subject.subject + '</span>\n' +
                  '                            <span style="color:#009688;cursor: pointer;" class="subject-jianjie" data-title="' + data.datas[i].subject.introduction + '" >介绍</span></td>\n' +
                  '                            <td>' + data.datas[i].classname + '</td>\n' +
                  '                            <td>' + year + "-" + mouth + "-" + date + "至" + data.datas[i].min_age + '</td>\n' +
                  '                            <td>' + day + data.datas[i].classroominfo.start + '---' + data.datas[i].classroominfo.jieshu + '</td>\n' +
                  '                            <td>' + data.datas[i].classroominfo.classRoom.name + '</td>\n' +
                  '                            <td>' + data.datas[i].keshi + '</td>\n' +
                  '                            <td>' + data.datas[i].money + '</td>\n' +
                  '                            <td>' + hl + '</td>\n' +
                  '                        </tr>'
              }
              $('#add_xk .r-table .r-tbody').html(html);
            }
          })
        }
      });
    })
    $(this).addClass('xk-bgs').siblings().removeClass('xk-bgs')
  })
  $(".a3").click(function (e) {
    selStudent();
    courseMajor();
    $(".r").eq(2).show().siblings('.r').hide();
    layer.open({
      type: 1,
      title: '温馨提醒',
      skin: 'layui-layer-molv',
      area: ['420px', '320px'], //宽高
      content: '<h2 style="margin-top: 20px">&emsp;选课的条件</h2> </br>&emsp;&emsp;1.&emsp;本班级对应的上课时间与您其他课程时间不冲突</br>&emsp;&emsp;2.&emsp;符合该相应课程的年龄要求 </br>&emsp;&emsp;3.&emsp;在开放选课的时间内</br>&emsp;&emsp;4.&emsp;本班级报名剩余人数大于0</br><h2 style="margin-top: 20px">&emsp;温馨提醒</h2></br>&emsp;&emsp;<span style="color:red">选课成功后，请根据提示，安排缴费。否则所选课程可能会失效。</span>'
    });
    e.stopPropagation(); //阻止冒泡
  });
  
  // 选课
  // $('body').on('click', '#add_xk table tbody .r-tbody-tr', function () { // 点击选择科目
  //   $(this).toggleClass('xk-bg').siblings().removeClass('xk-bg')
  // });
  // 课程选择---------------学科列表
  // $.post('/pjsys/findAllInterview.action', function (data) { //科目列表
  //   var frxx_form_hmtl = '';
  //   for (var i = 0; i < data.length; i++) {
  //     frxx_form_hmtl += '<span km-id="' + data[i].id + '" >' + data[i].subject + '</span>';
  //   }
  //   $('#add_xk .subject-all').html(frxx_form_hmtl)
  // })
  // $('.a5').click(function () {
  //   stuInterview();
  // })
  //========学生选课
  $('body').on('click', '.r-table .subject-xz', function () {
    var classinfo_id = $(this).attr('classinfo-id')
    var num = $(this).attr('data-num')
    var subject = $(this).attr('data-subject')
    if (num <= 0) {
      layer.msg('名额已满，请选别的班级报名吧！');
      return false;
    }
    if (subject == 1) {
      layer.msg('请先预约面试通过，再来选择选课');
      return false;
    }
    layer.confirm('&emsp;<h3>浦江镇青少年教育培训中心热烈欢迎广大少年儿童前来本中心参加培训和活动，通过培训和活动增长学生的课外知识、提高学生的综合能力。</h3><br>&emsp;为加强管理，提高教学质量，提升服务水平，本中心开通在线教务管理平台用于学生信息管理和简化报名流程，以下报名须知请大家务必认真阅读：</br>&emsp;一、本中心是由闵行区教育局批准、闵行区民政局登记的社会培训机构，所有教育教学培训项目及兴趣小组均在闵行区立跃路3889号开展。</br>&emsp;二、本中心所开设培训项目得报名流程采用在线教务管理平台选课，预约面试等方式进行，相关报名及面试时间节点将通过官方微信公众号和教务管理平台同步发布。</br>&emsp;三、报名请务必自行确认好对应班级上课时间、地点、年龄和学习层次，报名缴费成功后，不再调整班级。</br>&emsp;四、关于退费（课次以中心行事历为准）：</br><div class="layui-form"><table class="layui-table"><thead><tr><th>时间</th><th>退费金额</th></tr></thead><tbody><tr><td>缴费成功后~第一次课前</td><td>缴费金额*90%</td></tr><tr><td>第一次课后~第二次课前</td><td>缴费金额*80%</td></tr><tr><td>第二次课后</td><td>依据中心《特殊情况退学、退费管理条例》</td></tr></tbody></table></div></br>补充说明：</br>&emsp;由于目前管理平台暂未开放在线缴费，需要现场至财务处缴费，在线选课成功后，前往“我的课程”进行课程缴费，查询到选课成功信息，点击“申请缴费”并在规定时间内前往浦江镇青少年教育培训中心（立跃路3889号）二楼财务室进行缴费。逾期视同放弃，已选课程将自动失效。', {
      title: '报名须知',
      area: ['50%', '50%'],
      btn: ['确定', '取消']
      ,
      btn1: function () {
        layer.confirm('确定报名吗?</br>具体的选班结果缴费后由老师统一进行安排', {
          btn: ['确认', '取消']
        }, function () {
          var stu_id = $('#add_xk select[name="interest"]').val();
          $.post('pjsys/selectSubject.action', {
            stu_id: stu_id,
            classinfo_id: classinfo_id
          }, function (data) {
            // if (data == 2) {
            //   layer.msg('你的面试未通过，不能选课！', {
            //     time: 3000
            //   }, function () { })
            // }
            // if (data == 1) {
            //   layer.msg('恭喜您选课成功,可前往我的课程查询课程状态', {
            //     time: 3000
            //   }, function () { })
            // }
            // if (data == 0) {
            //   layer.msg('课程时间段有冲突，请选择其他课程。', {
            //     time: 3000
            //   }, function () { })
            // }
            if (data == 0) {
              layer.msg('课程时间段有冲突，请选择其他课程。', {
                time: 3000
              }, function () { })
            } else if (data == 1) {
              layer.alert('恭喜您选课成功，请于6月8日、9日到现场缴费完成报名,逾期不候。放弃名额请拨打电话64297817。谢谢您的配合！', {
                skin: 'layui-layer-molv'
                  ,
                closeBtn: 0
              });
            } else if (data == 2) {
              layer.msg('你的面试未通过，不能选课！', {
                time: 3000
              }, function () {})
            } else if (data == 3){
              layer.msg('当前班级名额已报满,请刷新页面获取最新可选课程,并选择其他课程！', {
                time: 3000
              }, function () { })
            }else{
              layer.msg('出错了,请联系技术人员！', {
                    time: 3000
              }, function () { })
            }
          })
        })
      }
    })
  })
  //科目介绍
  $('body').on('click', '.subject-jianjie', function () {
    var content = $(this).attr('data-title');
    var title = $(this).siblings('span').eq(0).text()
    layer.open({
      type: 1,
      anim: 2,
      // skin: 'layui-layer-rim', //加上边框
      skin: 'layui-layer-molv demo-class',
      title: title,
      area: ['800px', '400px'], //宽高
      content: ' &emsp;&emsp;' + content + '',
    });
  })
  // 面试科目
  $('body').on('click', '#add_xk .r-tbody .interview-xk', function () {
    $.post('/pjsys/findAllInterview.action', function (data) { //科目列表
      var ms_km = '<option value=""></option>';
      for (var i = 0; i < data.length; i++) {
        ms_km += '<option value="' + data[i].id + '">' + data[i].interviewSubject + '</option>';
      }
      $('#interview-subject').html(ms_km)
      form.render("select")
    })
    $.post('pjsys/findById.action', {
      user_id: user_id
    }, function (data) {
      //选课学生
      var ms_xs = '<option value=""></option>';
      for (var i = 0; i < data.length; i++) {
        ms_xs += '<option value="' + data[i].id + '">' + data[i].stuName + '</option>';
      }
      $('#interview-student').html(ms_xs)
      form.render("select")
    })
    $.post('pjsys/findBySubjectInterview.action', {
      interview_id: 3
    }, function (data) {
      // console.log(data);
      var ms_km = '<option value=""></option>';
      for (var i = 0; i < data.length; i++) {
        ms_km += '<option value="' + data[i].id + '">' + data[i].starttime + '---' + data[i].end_time + '</option>';
      }
      $('#interview-time').html(ms_km)
      form.render("select")
    })
    form.on('select(interview-subject)', function (data) {
      var interview_id = data.value
      $.post('/pjsys/findBySubjectInterview.action', {
        interview_id: interview_id
      }, function (data) { //面试科目
        var ms_km = '';
        // console.log(data);
        if (data == [] || data == '') {
          ms_km = '<option value="">暂时没有面试时间</option>';
        } else {
          for (var i = 0; i < data.length; i++) {
            ms_km += '<option value="' + data[i].id + '">' + data[i].starttime + '---' + data[i].end_time + '</option>';
          }
        }
        $('#interview-time').html(ms_km)
        form.render("select")
      })
    });
    $('.r-ms-kemu').show();
  })
  $(".r-ms-kemu-btn-del").click(function(){
    $(".r-ms-kemu").hide();
  });
  // 面试确认按钮
  $('.r-ms-kemu-btn').click(function () {
    var stu_id = $('#interview-student').val();
    var subject = $('#interview-subject').val();
    var interview_timeslot_id = $('#interview-time').val();
    console.log(stu_id);
    console.log(interview_timeslot_id);
    console.log(subject);
    if (stu_id == '' || stu_id == 'undefind') {
      layer.msg('请选择面试学生', {
        time: 2000
      })
    } else if (interview_timeslot_id == '' || interview_timeslot_id == 'undefind') {
      layer.msg('请选择面试时间', {
        time: 2000
      })
    } else {
      $.post('pjsys/selectInterview.action', {
        stu_id: stu_id,
        interview_timeslot_id: interview_timeslot_id
      }, function (data) {
        if (data == 1) {
          layer.msg('预约面试成功,您可以在兴趣小组下的预约记录中查看预约详情与预约结果', {
            time: 1000
          }, function () {
            $('.a5').click();
            $('.r-ms-kemu').hide();
          })
        }
      })
    }
  })
  // ===============课程选择结束==================
  // ===============预约记录开始==================
  /**
 * 面试预约列表
 */
function stuInterview () {
  $.post('pisys/findInterviewList', {
    user_id: user_id
  }, function (data) { //预约面试列表
    var ms_km = '';
    for (var i = 0; i < data.length; i++) {
      var result;
      if (data[i].result == 1) {
        result = '恭喜面试通过';
      } else if (data[i].result == 2) {
        result = '很遗憾面试未通过';
      } else if (data[i].result == 0) {
        result = '待审核';
      }
      ms_km += '<tr class="r-tbody-tr"><td>' + data[i].student.stuName + '</td><td>' + data[i].interview_timeslot.interview.interviewSubject + '</td><td>' + data[i].interview_timeslot.starttime + '</td><td>' + data[i].interview_timeslot.end_time + '</td><td>' + result + '</td></tr>';
    }
    $('.r-interview .r-tbody-ms').html(ms_km)
  })
}
// ===============预约记录结束==============
  // ==================我的课程开始================
  // 我的课程列表
  function stuSubject() {
    $.post('pisys/findClassinfo.action', {user_id: user_id}, function (data) {
      var subject_html = '';
      var flag;
      var day = '';
      var handle = '';
      for (var i = 0; i < data.length; i++) {
        if (data[i].flag === 1) {
          flag = '<span className="r-tbody-tr-dl" user-id="' + data[i].student.id + '">已报名,未申请缴费</span>';
        } else if (data[i].flag === 2) {
          flag = '<span className="r-tbody-tr-dl" user-id="' + data[i].student.id + '">已申请缴费,请及时到线下缴费</span>';
        } else if (data[i].flag === 3) {
          flag = '<span className="r-tbody-tr-dl" user-id="' + data[i].student.id + '">您已成功缴费</span>';
        } else if (data[i].flag === 4) {
          flag = '<span className="r-tbody-tr-dl" user-id="' + data[i].student.id + '">您已成功退班</span>';
        } else if (data[i].flag === 5) {
          flag = '<span className="r-tbody-tr-dl" user-id="' + data[i].student.id + '">已申请退费,请及时到线下退费</span>';
        } else if (data[i].flag === 6) {
          flag = '<span className="r-tbody-tr-dl" user-id="' + data[i].student.id + '">您已成功退费</span>';
        } else if (data[i].flag === 7) {
          flag = '<span className="r-tbody-tr-dl" user-id="' + data[i].student.id + '">已报名,未申请续费</span>';
        } else if (data[i].flag === 8) {
          flag = '<span className="r-tbody-tr-dl" user-id="' + data[i].student.id + '">已申请续费,请及时到线下续费</span>';
        } else if (data[i].flag === 9) {
          flag = '<span className="r-tbody-tr-dl" user-id="' + data[i].student.id + '">您已成功续费</span>';
        } else if (data[i].flag === 10) {
          flag = '<span className="r-tbody-tr-dl" user-id="' + data[i].student.id + '">您已被拒绝缴费</span>';
        } else if (data[i].flag === 11) {
          flag = '<span className="r-tbody-tr-dl" user-id="' + data[i].student.id + '">您已被拒绝续费</span>';
        } else if (data[i].flag === 12) {
          flag = '<span className="r-tbody-tr-dl" user-id="' + data[i].student.id + '">您已被拒绝退费</span>';
        }
        if (data[i].classinfo.classroominfo.day === 1) {
          day = '星期一'
        } else if (data[i].classinfo.classroominfo.day === 2) {
          day = '星期二'
        } else if (data[i].classinfo.classroominfo.day === 3) {
          day = '星期三'
        } else if (data[i].classinfo.classroominfo.day === 4) {
          day = '星期四'
        } else if (data[i].classinfo.classroominfo.day === 5) {
          day = '星期五'
        } else if (data[i].classinfo.classroominfo.day === 6) {
          day = '星期六'
        } else if (data[i].classinfo.classroominfo.day === 7) {
          day = '星期日'
        }
        // 2018-12-07 暂时关闭退费退班功能
        // if (data[i].flag === 1) {
        //   handle = '<span class="xg-bg jf-span" style="cursor:pointer;" user-id="' + data[i].student.id + '">申请缴费</span>';
        // } else if (data[i].flag === 3 || data[i].flag === 9) {
        //   handle = '<span class="del-bg tb-span" style="cursor:pointer;" user-id="' + data[i].student.id + '">申请退班</span>';
        // } else if (data[i].flag === 7) {
        //   handle = '<span class="xg-bg xf-span" style="cursor:pointer;" user-id="' + data[i].student.id + '">申请续费</span>';
        // } else if (data[i].flag === 4) {
        //   handle = '<span class="del-bg tf-span" style="cursor:pointer;" user-id="' + data[i].student.id + '">申请退费</span>';
        // } else {
        //   handle = '暂无可进行操作'
        // }
        if (data[i].flag === 1) {
          handle = '<span class="xg-bg jf-span" style="cursor:pointer;" user-id="' + data[i].student.id + '">申请缴费</span>';
        } else if (data[i].flag === 7) {
          handle = '<span class="xg-bg xf-span" style="cursor:pointer;" user-id="' + data[i].student.id + '">申请续费</span>';
        } else {
          handle = '暂无可进行操作'
        }
        subject_html += '<tr class="r-tbody-tr" ids ="' + data[i].id + '" flag="' + data[i].flag + '"><td>' + data[i].student.stuName + '</td><td>' + data[i].classinfo.subject.subject + '</td><td>' + data[i].classinfo.classname + '</td><td>' + day + data[i].classinfo.classroominfo.start + '---' + data[i].classinfo.classroominfo.jieshu + '</td><td>' + data[i].classinfo.classroominfo.classRoom.name + '</td><td>' + data[i].classinfo.keshi + '</td><td>' + data[i].classinfo.money + '</td><td>' + flag + '</td><td>' + handle + '</td></tr>'
      }
      $('.r-my-subject .r-tbody').html(subject_html)
    })
  }
  // 我的课程---缴费
  $('body').on('click', '.r-my-subject .r-tbody .r-tbody-tr .jf-span', function (e) {
    var ids = $(this).parent().parent().attr('ids')
    $.post('/pjsys/pay.action', {
      ids: ids,
      flag: 2
    }, function (data) {
      if (data == 1) {
        layer.msg('您已申请缴费,请于6月8日、9日到现场缴费完成报名,放弃名额请拨打电话64297817。谢谢配合!', {
          time: 3000
        }, function () {
          $('.a4').click()
        });
      } else {
        layer.msg('您已缴费失败,请再尝试缴费');
      }
    })
    e.stopPropagation()
  })
  // 我的课程------退班
  $('body').on('click', '.r-my-subject .r-tbody .r-tbody-tr .tb-span', function (e) {
    var ids = $(this).parent().parent().attr('ids')
    layer.confirm('&emsp;您如果现在退班的话,可以等到下一轮有名额申请的时候再进行线上申请新的班级报名', {
      title: '退班须知',
      btn: ['确认', '取消']
    }, function () {
      $.post('pjsys/quitSubject.action', {
        id: ids
      }, function (data) {
        if (data === 1) {
          layer.msg('您已申请退班', {
            time: 3000
          }, function () {
            $('.a4').click()
          });
        }
      });
    })
    e.stopPropagation()
  })
  // 我的课程------退费 只有退班之后才能退费
  $('body').on('click', '.r-my-subject .r-tbody .r-tbody-tr .tf-span', function (e) {
    var ids = $(this).parent().parent().attr('ids')
    layer.confirm('&emsp;退费需要携带缴费收据和开学通知材料,到线下进行退费,请知悉,谢谢!', {
      title: '退费须知',
      btn: ['确认', '取消']
    }, function () {
      $.post('/pjsys/pay.action', {
        ids: ids,
        flag: 5
      }, function (data) {
        if (data === 1) {
          layer.msg('您已申请退费,请及时到线下财务进行退费', {
            time: 3000
          }, function () {
            $('.a4').click()
          });
        } else {
          layer.msg('退费失败,请重新尝试')
        }
      });
    })
    e.stopPropagation()
  })
  // 我的课程------续费
  $('body').on('click', '.r-my-subject .r-tbody .r-tbody-tr .xf-span', function (e) {
    var ids = $(this).parent().parent().attr('ids')
    $.post('/pjsys/pay.action', {ids: ids,flag: 8}, function (data) {
      if (data === 1) {
        layer.msg('您已申请续费成功,请尽快到线下财务进行续费', {
          time: 3000
        }, function () {
          $('.a4').click();
        });
      } else {
        layer.msg('您已续费失败,请重新尝试');
      }
    })
    e.stopPropagation()
  })
  // =================我的课程结束===========
  // =================我的考勤开始=========
  // 考勤汇总
  $.post('/pjsys/findAttenceById.action', {user_id: user_id}, function (data) {
    var ms_km = '';
    for (var i = 0; i < data.length; i++) {
      ms_km += '<tr class="r-tbody-tr"><td>' + data[i].stuName + '</td><td>' + data[i].attence[1] + '天</td><td>' + data[i].attence[2] + '次</td></tr>';
    }
    $('#kq_box').html(ms_km)
  })
  // 缺勤日期
  element.on('tab(kaoqin)', function (data) {
    if ($(this).index() == 1) {
      $.post('/pjsys/UnAttence.action', {user_id: user_id}, function (data) {
        var kq = '';
        if (data.length < 0 || data == '') {
          kq += '<tr class="r-tbody-tr"><td colspan="3">暂时没有考勤记录</td></tr>'
        } else {
          for (var i = 0; i < data.length; i++) {
            kq += '<tr class="r-tbody-tr"><td>' + data[i].student.stuName + '</td><td>' + data[i].attence_time + '</td><td>' + data[i].reasons.reason + '</td></tr>';
          }
        }
        $('.r-kaoqin #kq').html(kq)
      })
    }
  })
  // ===============我的考勤结束============
  // 首页三条最新消息
  function LatestNews() {
    $.post('/pjsys/queryFirstThreeByImessage', {
      user_id: user_id
    }, function (data) {
      var html = ''
      for (var i = 0; i < data.length; i++) {
      // for (var i = 0; i < 1; i++) {
        html += '<div class="layui-col-md4"><div class="layui-card"><div class="layui-card-header" style="font-size: 26px;overflow: hidden;white-space: nowrap;text-overflow: ellipsis;">' + data[i][1] + '</div><div class="layui-card-body" style="font-size: 20px;">' + data[i][2] + '</div></div></div>'
      }
      $('.rl .layui-col-space15').html(html)
    })
  }
  LatestNews()
  // 全选消息
  $('body').on('click', ' .r-news .r-table .r-thead .r-thead-tr th input[type="checkbox"]', function () {
    $('.r-news .r-table .r-tbody .r-tbody-tr td input[type="checkbox"]').prop("checked", $(this).prop("checked"));
  });
  $('body').on('click', ' .r-news .r-table .r-tbody .r-tbody-tr td input[type="checkbox"]', function () {
    $(this).prop("checked", $(this).prop("checked"));
  });
  // 标记多条已读
  $('.r-news .news-red').click(function () {
    var checked = [];
    for (var i = 0; i < $('.r-news .r-table .r-tbody .r-tbody-tr td input[type="checkbox"]').length; i++) {
      if ($('.r-news .r-table .r-tbody .r-tbody-tr td input[type="checkbox"]').eq(i).is(':checked')) {
        checked.push($('.r-news .r-table .r-tbody .r-tbody-tr td input[type="checkbox"]').eq(i).attr('data-id'))
      }
    }
    checked = checked.join(',');
    $.post('pjsys/updateFlagBySelect.action', {ids: checked,flag: 1}, function (data) {
      if (checked == []) {
        layer.msg('请至少选择一个消息,进行标记')
      } else {
        if (data === 1) {
          layer.msg('您已标记成功', {
            time: 2000
          }, function () {
            news()
            dec()
          });
        } else {
          layer.msg('标记失败,请重新尝试');
        }
      }
    })
  })
  // 删除多条消息
  $('.r-news .news-del').click(function () {
    var checked = [];
    for (var i = 0; i < $('.r-news .r-table .r-tbody .r-tbody-tr td input[type="checkbox"]').length; i++) {
      if ($('.r-news .r-table .r-tbody .r-tbody-tr td input[type="checkbox"]').eq(i).is(':checked')) {
        checked.push($('.r-news .r-table .r-tbody .r-tbody-tr td input[type="checkbox"]').eq(i).attr('data-id'))
      }
    }
    checked = checked.join(',');
    $.post('pjsys/deleteAll.action', {ids: checked,}, function (data) {
      if (checked == []) {
        layer.msg('请至少选择一个消息,进行删除')
      } else {
        if (data === 1) {
          layer.msg('您已删除成功', {time: 2000}, function () {
            news()
            dec()
          });
        } else {
          layer.msg('删除失败,请重新尝试');
        }
      }
    })
  })
  // 未读消息
  function news() {
    $.post('/pjsys/countMessage.action', {
      user_id: user_id
    }, function (data) {
      if (data === 0) {
        $('.spans').remove()
      } else {
        $('.spans').html(data)
      }
    })
  }
  news()
  // 消息列表
  function dec() {
    $.post('/pjsys/findMessage.action', {
      user_id: user_id,
      page: 1
    }, function (data) {
      total = data.total;
      if (data.datas.length < 0) {
        $('.r-news  .r-tbody-all').html('<tr class="r-tbody-tr"><td colspan="4">暂时没有信息</td></tr>')
      } else {
        laypage.render({
          elem: 'news' //注意，这里的 test1 是 ID，不用加 # 号
            ,
          count: total //数据总数，从服务端得到
            ,
          theme: "#65daf7",
          first: '首页',
          last: '尾页',
          jump: function (obj, first) {
            $.post('/pjsys/findMessage.action', {
              user_id: user_id,
              page: obj.curr
            }, function (data) {
              var html = '';
              var flag = '';
              var content =''
              for (var i = 0; i < data.datas.length; i++) {
                if (data.datas[i].flag === 1) {
                  flag = '已读',
                    content = data.datas[i].message
                    
                    html += '<tr style="background: #eee" class="r-tbody-tr " data-id="' + data.datas[i].id + '"><td><input type="checkbox" name="news" data-id="' + data.datas[i].id + '"></td><td class="allNew" style="overflow: hidden;text-overflow: ellipsis;white-space: nowrap;">' + data.datas[i].head + '</td><td class="allNew" style="overflow: hidden;text-overflow: ellipsis;white-space: nowrap;">' + content + '</td><td class="allNew">' + data.datas[i].time + '</td><td class="allNew">' + flag + '</td><td><button class="bg btn-show-news" data-id="' + data.datas[i].id + '">查看详情</button><button class="del-bg btn-del" data-id="' + data.datas[i].id + '">删除消息</button></td></tr>'
                    // html += '<tr style="background: #eee" class="r-tbody-tr " data-id="' + data.datas[i].id + '"><td><input type="checkbox" name="news" data-id="' + data.datas[i].id + '"></td><td class="allNew" style="overflow: hidden;text-overflow: ellipsis;white-space: nowrap;">' + data.datas[i].head + '</td><td class="allNew" style="overflow: hidden;text-overflow: ellipsis;white-space: nowrap;">' + data.datas[i].message + '</td><td class="allNew">' + data.datas[i].time + '</td><td class="allNew">' + flag + '</td><td><button class="bg btn-show-news" data-id="' + data.datas[i].id + '">查看详情</button><button class="del-bg btn-del" data-id="' + data.datas[i].id + '">删除消息</button></td></tr>'
                } else {
                  flag = '未读'
                  html += '<tr class="r-tbody-tr " data-id="' + data.datas[i].id + '"><td><input type="checkbox" name="news" data-id="' + data.datas[i].id + '"></td><td class="allNew" style="overflow: hidden;text-overflow: ellipsis;white-space: nowrap;">' + data.datas[i].head + '</td><td class="allNew" style="overflow: hidden;text-overflow: ellipsis;white-space: nowrap;max-height:50px">' + data.datas[i].message + '</td><td class="allNew">' + data.datas[i].time + '</td><td class="allNew">' + flag + '</td><td><button class="bg btn-show-news" data-id="' + data.datas[i].id + '">查看详情</button><button class="del-bg btn-del" data-id="' + data.datas[i].id + '">删除消息</button></td></tr>'
                }
              }
              $('.r-news .r-tbody-all').html(html).find('br').remove()
            })
          }
        });
      }
    })
  }

  // 标记以读
  $('body').on('click', '.btn-read', function () {
    $.post('/pjsys/updateFlagMessage.action', {
      id: $(this).attr('data-id')
    }, function (data) {
      if (data === 1) {
        layer.msg('操作成功');
        news()
        dec()
      }
    })
  })
  // 删除消息
  $('body').on('click', '.r-news .btn-del', function () {
    id = $(this).attr('data-id')
    $.post('/pjsys/deleteMessage.action', {id: id}, function (data) {
      if (data === 1) {
        layer.msg('删除成功')
        news()
        $('.a12').click()
      }
    })
  })
  // 各自学生消息
  // 学生列表
  function student_lists(e) {
    $.post('/pjsys/TeachingSystem/findById.action', {user_id: user_id}, function (data) {
      var html = '';
      for (i = 0; i < data.length; i++) {
        html += '<span data-id="' + data[i].id + '">' + data[i].stuName + '</span>'
      }
      $('.r-news .student-news').html(html)
    })
    e.stopPropagation()
  }
  element.on('tab(news-tab)', function (data) {
  if ($(this).index() == 1) {
    $('.r-news .r-table-class .r-tbody').html('<tr class="r-tbody-tr"><td colspan="4">暂时没有班级信息</td></tr>')
    $('body').on('click', '.r-news .student-news span', function (e) {
      var total = ''
      var id = ''
      id = $(this).attr('data-id')
      $.post('/pjsys/findimessageByClass.action', {stu_id: id,page: 1}, function (data) {
        // console.log(data);
        total = data.total
        laypage.render({
          elem: 'news-student'
          ,count: total
          ,theme: "#65daf7"
          ,first: '首页'
          ,last: '尾页'
          ,jump: function (obj, first) {
            if (total == 0 || total == '') {
              $('.r-news .r-table-class .r-tbody').html('<tr class="r-tbody-tr"><td colspan="4">没有查询到该学生信息</td></tr>')
            } else {
              $.post('/pjsys/findimessageByClass.action', {
                stu_id: id,
                page: obj.curr
              }, function (news) {
                var html = '';
                var flag = '';
                for (var i = 0; i < news.datas.length; i++) {
                  html += '<tr class="r-tbody-tr" data-id="' + data.datas[i].id + '"><td class="studentNew" style="overflow: hidden;text-overflow: ellipsis;white-space: nowrap;">' + news.datas[i].head + '</td><td class="studentNew" style="overflow: hidden;text-overflow: ellipsis;white-space: nowrap;">' + news.datas[i].message + '</td><td class="studentNew">' + news.datas[i].time + '</td><td><button class="bg btn-read-stuent" data-id="' + data.datas[i].id + '">查看详情</button></td></tr>'
                }
                $('.r-news .r-table-class .r-tbody').html(html)
              })
            }
          }
        });
      })
      $(this).addClass('xk-bgs').siblings().removeClass('xk-bgs')
      e.stopPropagation()
    })
  } else if ($(this).index() == 2) {
    // 面试学生列表
    $.post('/pjsys/pisys/findInterviewList', {
      user_id: user_id
    }, function (data) {
      console.log(data);
      var html = '';
      for (i = 0; i < data.length; i++) {
        html += '<span data-id="' + data[i].student.id + '">' + data[i].student.stuName + '</span>'
      }
      $('.r-news .student-Interview').html(html)
    })
    // 初始值
    $('.r-news .r-table-Interview .r-tbody').html('<tr class="r-tbody-tr"><td colspan="4">暂时没有面试信息</td></tr>')
    // 面试学生消息
    $('body').on('click', '.r-news .student-Interview span', function (e) {
      var id = $(this).attr('data-id')
      $.post('/pjsys/findImessageByInter.action', {
        stu_id: id,
        page: 1
      }, function (data) {
        var total = '';
        total = data.total;
        laypage.render({
          elem: 'news-Interview',
          count: total,
          theme: "#65daf7",
          first: '首页',
          last: '尾页',
          jump: function (obj, first) {
            if (total == '' || total == 0) {
              $('.r-news .r-table-Interview .r-tbody').html('<tr class="r-tbody-tr"><td colspan="4">暂时没有面试信息</td></tr>')
            } else {
              $.post('/pjsys/findImessageByInter.action', {stu_id: id,page: obj.curr}, function (data) {
                var html = '';
                for (var i = 0; i < data.datas.length; i++) {
                  // html+='<tr class="r-tbody-tr"><td>' + data.datas[i].head + '</td><td style="overflow: hidden;text-overflow: ellipsis;white-space: nowrap;">' + flag + '</td><td>' + data.datas[i].time + '</td><td><button class="bg btn-Interview-stuent" data-id="' + data.datas[i].id + '">查看详情</button></td></tr>'
                  html += '<tr class="r-tbody-tr " data-id="' + data.datas[i].id + '"><td class="innerviewNew" style="overflow: hidden;text-overflow: ellipsis;white-space: nowrap;">' + data.datas[i].head + '</td><td class="innerviewNew" style="overflow: hidden;text-overflow: ellipsis;white-space: nowrap;">' + data.datas[i].message + '</td><td class="innerviewNew">' + data.datas[i].time + '</td><td><button class="bg btn-Interview-stuent" data-id="' + data.datas[i].id + '">查看详情</button></td></tr>'
                }
                $('.r-news .r-table-Interview .r-tbody').html(html)
              })
            }
          }
        })
      })
      $(this).addClass('xk-bgs').siblings().removeClass('xk-bgs')
      e.stopPropagation()
    })
  }
  })
  // 消息详情
  $('body').on('click', '.layui-tab-content .allNew', function () {
    var ids = $(this).parent().attr('data-id')
    news()
    allNew(ids)
    readeMe(ids)
    dec()
  })
  $('body').on('click', '.btn-show-news', function () {
    var ids = $(this).attr('data-id')
    allNew(ids)
    news()
  })
// 全部消息
  function allNew(ids) {
    $.post('/pjsys/XQMessage.action', {id: ids}, function (data) {
      layer.open({
        type: 1,
        anim: 2,
        title: data.head,
        end: function () {
          news()
        },
        skin: 'layui-layer-molv demo-class',
        area: ['800px', '400px'], //宽高
        content: '&emsp;&emsp;' + data.message
      });
    })
  }
  // 标记已读
  function readeMe(ids) {
    news()
    $.post('/pjsys/updateFlagMessage.action', {id: ids}, function (data) {
      if (data === 1) {
      }
    })
  }
  //学生信息查看详情
  $('body').on('click', '.layui-tab-content .studentNew', function () {
    var ids = $(this).parent().attr('data-id')
    studentNew(ids)
  })
  $('body').on('click', '.btn-read-stuent', function () {
    var ids = $(this).attr('data-id')
    studentNew(ids)
  })
  // 信息详情
  function studentNew(ids) {
    $.post('/pjsys/XQMessageByClass.action', {id: ids}, function (data) {
      layer.open({
        type: 1,
        anim: 2,
        title: data.head,
        end:function(){
          news()
        },
        skin: 'layui-layer-molv demo-class',
        area: ['800px', '400px'], //宽高
        content: '&emsp;&emsp;' + data.message
      });
    })
  }
  // 面试学生查看详情
  $('body').on('click', '.layui-tab-content .innerviewNew', function () {
    var ids = $(this).parent().attr('data-id')
    interviewNew(ids)
  })
  $('body').on('click', '.btn-Interview-stuent', function () {
    var ids = $(this).attr('data-id')
    interviewNew(ids)
  })
  // 面试消息详情
  function interviewNew(ids) {
    $.post('/pjsys/XQMessageByInter.action', {id: ids}, function (data) {
      layer.open({
        type: 1,
        anim: 2,
        title: data.head,
        skin: 'layui-layer-molv demo-class',
        area: ['800px', '400px'], //宽高
        content: '&emsp;&emsp;' + data.message
      });
    })
  }

// ===========消息结束=========

  $('.aside-ul-li dl dd').click(function (e) {
    $('.rl').hide()
    e.stopPropagation(); //阻止冒泡
  })
  $(".a1").click(function (e) {
    stuInfo();
    $(".r").eq(0).show().siblings('.r').hide();
    e.stopPropagation(); //阻止冒泡
  });
  // 添加学生
  $(".a2").click(function (e) {
    $(".r").eq(1).show().siblings('.r').hide();
    e.stopPropagation(); //阻止冒泡
  });
  // // 我的课程
  $(".a4").click(function (e) {
    // stuInterview();
    stuSubject();
    $(".r").eq(3).show().siblings('.r').hide();
    e.stopPropagation(); //阻止冒泡
  });
  // // 预约面试
  $(".a5").click(function (e) {
    stuInterview()
    $(".r").eq(4).show().siblings('.r').hide();
    e.stopPropagation(); //阻止冒泡
  });
  // 我的考勤
  $(".a10").click(function (e) {
    $(".r").eq(9).show().siblings('.r').hide();
    e.stopPropagation(); //阻止冒泡
  });
  // 日历
  $(".a11").click(function (e) {
    $(".r").eq(10).show().siblings('.r').hide();
    e.stopPropagation(); //阻止冒泡
  });
  $(".a12").click(function (e) {
    dec()
    student_lists(e)
    $(".r").eq(11).show().siblings('.r').hide();
    e.stopPropagation(); //阻止冒泡
  });
  // 消息
  $('.news').click(function (e) {
    dec()
    $(".r").eq(11).show().siblings('.r').hide();
    e.stopPropagation(); //阻止冒泡
  })
})
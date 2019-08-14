layui.use(['layer', 'table','upload' ,'form', 'laydate', 'table', 'laypage', 'element'], function () {
  var layer = layui.layer,
    form = layui.form,
    upload = layui.upload,
    laydate = layui.laydate,
    laypage = layui.laypage,
    element = layui.element;
    table = layui.table;
  // ================检测是否登陆开始================
  var user_id = $.cookie('user_id');
  var user_role = $.cookie('user_role');
  if (user_id == '' || user_id == undefined) {
    window.location.href = "login.html";
  }
  // ================检测是否登陆结束================
  //=================页面权限开始
  //1 教务 2 财务 3 班主任 4 管理员
  //======教务
  if (user_role == 1) {
    $('aside ul li').eq(1).hide()
    $('aside ul li').eq(2).hide()
    $('aside ul li').eq(3).hide()
    //=====================班主任
    // 数据汇总导出
    $('.r-bzr-xskq .r-btn-kq').attr('class', 'btns');
    $('.r-bzr-xskq .btn-xg-kaoqin').attr('class', 'btns');
    //=====================财务
    // 按姓名查找
    $('.r-cw-jfsh .jf-search-btn').attr('class', 'btns');
    //确认缴费
    $('.r-cw-jfsh .jf-btn').attr('class', 'btns');
    // 按姓名查找
    $('.r-cw-xfsh .xf-search-btn').attr('class', 'btns');
    //确认续费
    $('.r-cw-xfsh .xf-btn').attr('class', 'btns');
    // 按姓名查找
    $('.r-cw-tfsh .tf-search-btn').attr('class', 'btns');
    //确认退费
    $('.r-cw-tfsh .tf-btn').attr('class', 'btns');
    //数据汇总导出
    $('.r-cw-sjhz .r-sjhz-excel').attr('class', 'btns');
    //数据汇总缴费明细导出
    $('.r-cw-sjhz .r-sjhz-excel').attr('class', 'btns');
    $('.r-cw-sjhz .r-excel').attr('class', 'btns');
    //  $('.r-cw-sjhz .r-sjhz-excel').attr('class','btns');
    //   =====================管理员
    $('.nav-admin-zhgl').removeClass('nav-admin-zhgl')
    $('.nav-admin-operation-log').removeClass('nav-admin-operation-log')
    $('.r-admin .r-zhgl-xz').attr('class', 'btns');
  }
  //财务
  else if (user_role == 2) {
    $('aside ul li').eq(0).hide()
    $('aside ul li').eq(1).hide()
    $('aside ul li').eq(3).hide()
    // ============教务
    // 学科管理新增学科
    $('.r-jw-major .major-add').attr('class', 'btns');
    // 面试科目管理新增面试科目
    $('.r-jw-kmgl .msxk-span2').attr('class', 'btns');
    // 面试科目管理新增面试场次
    $('.r-jw-kmgl .msxk-span3').attr('class', 'btns');
    // 科目管理新增科目
    $('.r-jw-xkgl .r-xkgl-xzxk').attr('class', 'btns');
    // 班级管理查找班级
    $('.r-jw-bjgl .btn-class-search').attr('class', 'btns');
    // 班级管理新增班级
    $('.r-jw-bjgl .r-bjgl-xz').attr('class', 'btns');
    // 班级管理升级班级
    $('.r-jw-bjgl .r-bjgl-sj').attr('class', 'btns');
    // 班级管理合并班级
    $('.r-jw-bjgl .r-bjgl-hb').attr('class', 'btns');
    // 面试审核查找
    $('.r-jw-mssh .search-interview-student').attr('class', 'btns');
    // 教室管理新增教室
    $('.r-jw-jsgl .r-jsgl-xz').attr('class', 'btns');
    // 教室管理新增时间段
    $('.r-jw-jsgl .r-jsgl-timer').attr('class', 'btns');
    // 教室管理教室占有率
    $('.r-jw-jsgl .r-jsgl-zyl').attr('class', 'btns');
    // 学生管理按姓名查找
    $('.r-bzr-ckxsxx .find-studentname').attr('class', 'btns');
    // 学生管理倒序
    $('.r-bzr-ckxsxx .daoxu').attr('class', 'btns');
    // 学生管理导出
    $('.r-bzr-ckxsxx .export-btn').attr('class', 'btns');
    // 学生考勤数据导出
    $('.r-jw-kq .dc').attr('class', 'btns');
    // 数据汇总导出
    $('.r-jw-sjhz .btn').attr('class', 'btns');
    // 站内信
    $('.r-jw-news .btn').attr('class', 'btns');
    //=====================班主任
    // 数据汇总导出
    $('.r-bzr-xskq .r-btn-kq').attr('class', 'btns');
    $('.r-bzr-xskq .btn-xg-kaoqin').attr('class', 'btns');
    //   =====================管理员
    $('.r-admin .r-zhgl-xz').attr('class', 'btns');
    $('.nav-admin-zhgl').removeClass('nav-admin-zhgl')
    $('.nav-admin-operation-log').removeClass('nav-admin-operation-log')
  }
  //班主任
  else if (user_role == 3) {
    $('aside ul li').eq(0).hide()
    $('aside ul li').eq(2).hide()
    $('aside ul li').eq(3).hide()
    // ============教务
    // 学科管理新增学科
    $('.r-jw-major .major-add').attr('class', 'btns');
    // 面试科目管理新增面试科目
    $('.r-jw-kmgl .msxk-span2').attr('class', 'btns');
    // 面试科目管理新增面试场次
    $('.r-jw-kmgl .msxk-span3').attr('class', 'btns');
    // 科目管理新增科目
    $('.r-jw-xkgl .r-xkgl-xzxk').attr('class', 'btns');
    // 班级管理查找班级
    $('.r-jw-bjgl .btn-class-search').attr('class', 'btns');
    // 班级管理新增班级
    $('.r-jw-bjgl .r-bjgl-xz').attr('class', 'btns');
    // 班级管理升级班级
    $('.r-jw-bjgl .r-bjgl-sj').attr('class', 'btns');
    // 班级管理合并班级
    $('.r-jw-bjgl .r-bjgl-hb').attr('class', 'btns');
    // 面试审核查找
    $('.r-jw-mssh .search-interview-student').attr('class', 'btns');
    // 教室管理新增教室
    $('.r-jw-jsgl .r-jsgl-xz').attr('class', 'btns');
    // 教室管理新增时间段
    $('.r-jw-jsgl .r-jsgl-timer').attr('class', 'btns');
    // 教室管理教室占有率
    $('.r-jw-jsgl .r-jsgl-zyl').attr('class', 'btns');
    // 站内信
    $('.r-jw-news .btn').attr('class', 'btns');
    // 学生管理按姓名查找
    $('.r-bzr-ckxsxx .find-studentname').attr('class', 'btns');
    // 学生管理倒序
    $('.r-bzr-ckxsxx .daoxu').attr('class', 'btns');
    // 学生管理导出
    $('.r-bzr-ckxsxx .export-btn').attr('class', 'btns');
    // 学生考勤数据导出
    $('.r-jw-kq .dc').attr('class', 'btns');
    // 数据汇总导出
    $('.r-jw-sjhz .btn').attr('class', 'btns');
    //=====================财务
    // 按姓名查找
    $('.r-cw-jfsh .jf-search-btn').attr('class', 'btns');
    //确认缴费
    $('.r-cw-jfsh .jf-btn').attr('class', 'btns');
    // 按姓名查找
    $('.r-cw-xfsh .xf-search-btn').attr('class', 'btns');
    //确认续费
    $('.r-cw-xfsh .xf-btn').attr('class', 'btns');
    // 按姓名查找
    $('.r-cw-tfsh .tf-search-btn').attr('class', 'btns');
    //确认退费
    $('.r-cw-tfsh .tf-btn').attr('class', 'btns');
    //数据汇总导出
    $('.r-cw-sjhz .r-sjhz-excel').attr('class', 'btns');
    //数据汇总缴费明细导出
    $('.r-cw-sjhz .r-sjhz-excel').attr('class', 'btns');
    $('.r-cw-sjhz .r-excel').attr('class', 'btns');
    //   =====================管理员
    $('.r-admin .r-zhgl-xz').attr('class', 'btns');
    $('.nav-admin-zhgl').removeClass('nav-admin-zhgl')
    $('.nav-admin-operation-log').removeClass('nav-admin-operation-log')
  }
  //=================页面权限结束
  //================退出登录开始================
  $('.btn-del-id').click(function () {
    $.cookie('user_id', '');
    $.cookie('user_role', '');
    window.location.href = "login.html";
  })
  //================退出登录结束================



// 多选下拉框

var formSelects = layui.formSelects;
formSelects.undisabled('select7_1');
formSelects.on('select7_1', function (id,vals) {
}, true);


  // ================执行时间输入开始================
  //执行一个laydate实例
  laydate.render({
    elem: '#subject-start' //指定元素
      ,
    type: 'datetime',
    format: 'yyyy-MM-dd HH:mm'
  })
  laydate.render({
    elem: '#subject-end' //指定元素
      ,
    type: 'datetime',
    format: 'yyyy-MM-dd HH:mm'
  })
  laydate.render({
    elem: '#xg-subject-start' //指定元素
      ,
    type: 'datetime',
    format: 'yyyy-MM-dd HH:mm'
  })
  laydate.render({
    elem: '#xg-subject-end' //指定元素
      ,
    type: 'datetime',
    format: 'yyyy-MM-dd HH:mm'
  })
  laydate.render({
    elem: '#xz-class-start-timer',
    type: 'datetime',
    format: 'yyyy-MM-dd HH:mm'
  })
  laydate.render({
    elem: '#xz-class-end-timer',
    type: 'datetime',
    format: 'yyyy-MM-dd HH:mm'
  })
  laydate.render({
    elem: '#xg-class-start-timer',
    type: 'datetime',
    format: 'yyyy-MM-dd HH:mm'
  })
  laydate.render({
    elem: '#xg-class-end-timer',
    type: 'datetime',
    format: 'yyyy-MM-dd HH:mm'
  })
  laydate.render({
    elem: '#clssroom-start-time',
    type: 'time',
    format: 'HH:mm'
  })
  laydate.render({
    elem: '#clssroom-end-time',
    type: 'time',
    format: 'HH:mm'
  })
  laydate.render({
    elem: '#export-end'

  })
  laydate.render({
    elem: '#export-start'
  })
  laydate.render({
    elem: '#xg-min-age'
  })
  laydate.render({
    elem: '#xg-max-age'
  })
  laydate.render({
    elem: '#xz-min-age'
  })
  laydate.render({
    elem: '#xz-max-age'
  })
  //================ 执行时间输入结束================
  //================ 执行分页开始================
  //执行一个laypage实例
  laypage.render({
    elem: 'auditing-interview',
    count: 0,
    theme: "#65daf7",
    first: '首页',
    last: '尾页'
  });
  laypage.render({
    elem: 'student-attendance',
    count: 0,
    theme: "#65daf7"
  });

  laypage.render({
    elem: 'student-attendance-summary',
    count: 0,
    theme: "#65daf7"
  });
  laypage.render({
    elem: 'pay',
    count: 0,
    theme: "#65daf7"
  });

  laypage.render({
    elem: 'renew',
    count: 0,
    theme: "#65daf7"
  });
  laypage.render({
    elem: 'refund',
    count: 0,
    theme: "#65daf7"
  });

  laypage.render({
    elem: 'payment-details',
    count: 0,
    theme: "#65daf7"
  });
  //发票
  laypage.render({
    elem: 'invoice',
    count: 0,
    theme: "#65daf7"
  });
  //教室排班
  laypage.render({
    elem: 'jspb-page',
    count: 0,
    theme: "#65daf7"
  });
  //教室排班
  //  laypage.render({
  //     elem: 'jw-student-attendance-summary'
  //      , count: 30
  //      , theme: "#65daf7"
  //  });
  //================ 执行分页结束================
  //================教务端开始================

  //----------学科管理开始----------
  //学科管理
  $('.nav-major').on('click', function (e) {
    $.post('pjsys/findAllMajors.action', function (data) {
      var html;
      if (data == null) {
        html += '<tr class="r-tbody-tr"><td colspan="2">暂无数据</td></tr>'
      } else {
        for (var i = 0; i < data.length; i++) {
          if (user_role == 1 || user_role == 4) {
            html += '<tr class="r-tbody-tr">\n' +
              '                <td>' + data[i].major + '</td>\n' +
              '                <td><span class="xg-bg xg-major" data-id="' + data[i].id + '" data-major="' + data[i].major + '" >修改学科</span>\n' +
              '                    <span class="del-bg del-major" data-id="' + data[i].id + '">删除学科</span>\n' +
              '                </td>\n' +
              '            </tr>'
          } else {
            html += '<tr class="r-tbody-tr">\n' +
              '                <td>' + data[i].major + '</td>\n' +
              '                <td><span class="xg-bgs" data-id="' + data[i].id + '" data-major="' + data[i].major + '" >修改学科</span>\n' +
              '                    <span class="xg-bgs" data-id="' + data[i].id + '">删除学科</span>\n' +
              '                </td>\n' +
              '            </tr>'
          }
        }
      }
      $('.r-jw-major .r-table .r-tbody').html(html)
    })
    $('.r-jw-major').show().siblings().hide();
    e.stopPropagation()
  })
  //新增学科
  $('body').on('click', '.major-add', function () {
    $('.fade-major').show()
  })
  //取消新增
  $('.fade-major-del').on('click', function () {
    $('.fade-major').hide()
  })
  //确定新增
  $('.fade-major-add').on('click', function () {
    var text = $('.fade-major .fade-center .fade-center-left input[type="text"]').val();
    if (text == '') {
      layer.msg('内容不能为空');
      return false;
    }
    $.post('pjsys/addMajors.action', {
      major: text
    }, function (data) {
      if (data === 1) {
        layer.msg('新增成功', {
          time: 1000
        }, function () {
          $('.nav-major').click()
        });
      } else {
        layer.msg('新增失败');
      }
      $('.fade-major').hide()
    })
  })
  $('.r-jw-major').on('click', '.del-major', function (data) {
    var id = $(this).attr('data-id')
    $.post('pjsys/deleteMajors.action', {
      id: id
    }, function (data) {
      if (data === 1) {
        layer.msg('删除成功', {
          time: 1000
        }, function () {
          $('.nav-major').click()
        });
      } else {
        layer.msg('删除失败');
      }
    })
  })
  //修改学科
  $('body').on('click', '.xg-major', function () {
    var id = $(this).attr('data-id');
    var major = $(this).attr('data-major');
    $('.fade-xg-major .fade-center .fade-center-left input[type="text"]').val(major)
    $('.fade-xg-major .fade-center .fade-center-left .fade-xg-major-add').attr('data-id', id);
    $('.fade-xg-major').show()
  })
  //取消修改
  $('.fade-xg-major-del').on('click', function () {
    $('.fade-xg-major').hide()
  })
  //确定修改
  $('.fade-xg-major-add').on('click', function () {
    var id = $(this).attr('data-id');
    var major = $('.fade-xg-major .fade-center .fade-center-left input[type="text"]').val()
    $.post('pjsys/updateMajors.action', {
      id: id,
      major: major
    }, function (data) {
      if (data === 1) {
        layer.msg('修改成功', {
          time: 1000
        }, function () {
          $('.nav-major').click()
        });
      } else {
        layer.msg('修改失败');
      }
    })
    $('.fade-xg-major').hide()
  })

  //----------学科管理结束----------



  //----------面试科目管理开始----------
  $('.nav-kmgl').on('click', function (e) {
    $('.r-jw-kmgl').show().siblings().hide();
    $.post('pjsys/findAlltimeslot.action', function (data) {
      var frxx_form_hmtl = '';
      for (var i = 0; i < data.length; i++) {
        if (user_role == 1 || user_role == 4) {
          frxx_form_hmtl += '<tr class="r-tbody-tr"><td>' + data[i].interview.interviewSubject + '</td><td>' + data[i].num + '</td><td>' + data[i].starttime + '</td><td>' + data[i].end_time + '</td><td><span class="xg-bg btn-xg-interview-subject" kmid="' + data[i].interview.id + '" kmname="' + data[i].interview.interviewSubject + '">修改科目</span> <span class="xg-bg btn-xg-interview-subject-time" mskm="' + data[i].interview.interviewSubject + '" msrs="' + data[i].num + '" starttime="' + data[i].starttime + '" end_time="' + data[i].end_time + '" index-id="' + data[i].id + '">修改场次</span></td></tr>';
        } else {
          frxx_form_hmtl += '<tr class="r-tbody-tr"><td>' + data[i].interview.interviewSubject + '</td><td>' + data[i].num + '</td><td>' + data[i].starttime + '</td><td>' + data[i].end_time + '</td><td><span class="xg-bgs" kmid="' + data[i].interview.id + '" kmname="' + data[i].interview.interviewSubject + '">修改科目</span> <span class="xg-bgs" mskm="' + data[i].interview.interviewSubject + '" msrs="' + data[i].num + '" starttime="' + data[i].starttime + '" end_time="' + data[i].end_time + '" index-id="' + data[i].id + '">修改场次</span></td></tr>';
        }

      }
      $('.r-tbody-msxk').html(frxx_form_hmtl)
    })
    e.stopPropagation()
  })
  // 新增面试学科
  $('.msxk-span2').click(function () {
    $('.fade-xzms').show();
  })
  $('.fade-xzmsxk-btn').on('click', function () {
    var text = $('.fade-xzms .fade-subject .fade-center-left .layui-input-block input').val();
    if (text == '') {
      layer.msg('内容不能为空');
      return false;
    }
    $.post('/pjsys/addInterviewSubject.action', {
      interviewSubject: text
    }, function (data) {
      if (data === 1) {
        layer.msg('新增成功', {
          time: 1000
        }, function () {
          $('.fade-xzms').hide();
          $('.nav-kmgl').click()
        });
      } else {
        layer.msg('新增失败');
      }
    })
  })
  $('.fade-xzmsxk-btn-del').click(function () {
    $('.fade-xzms').hide();
  })
  // // 修改面试时间段
  // $('body').on('click', '.r-jw-kmgl .btn-xg-interview-subject', function () {
  //   var id = $(this).attr('kmid');
  //   var kmname = $(this).attr('kmname');
  //   $('.fade-xg-interview-subject input').val(kmname);
  //   $('.fade-xg-interview-subject input').attr('data-id', id);
  //   $('.fade-xg-interview-subject').show();
  // })
  // $('.btn-subject-interview-del').on('click', function () {
  //   $('.fade-xg-interview-subject').hide();
  // })
  // $('.btn-subject-interview').on('click', function () {
  //   var interviewSubject = $('.fade-xg-interview-subject input').val();
  //   var id = $('.fade-xg-interview-subject input').attr('data-id');
  //   $.post('/pjsys/updateInterviewSubject.action', {
  //     interviewSubject: interviewSubject,
  //     id: id
  //   }, function (data) {
  //     if (data === 1) {
  //       layer.msg('修改成功', {
  //         time: 1000
  //       }, function () {
  //         $('.fade-xg-interview-subject').hide();
  //         $('.nav-kmgl').click()
  //       });
  //     } else {
  //       layer.msg('修改失败');
  //     }
  //   });
  // })
  // 修改面试场次
  // $('body').on('click', '.r-jw-xkgl .r-table .btn-xg-interview-subject-time', function () {
  //   var kmname = $(this).attr('index_name');
  //   var msrs ;
  //   var starttime ;
  //   var end_time ;
  //   var id = $(this).attr('index_id');
  //   $.post("/pjsys/queryInterviewtimeslotActionByinterviewid", { interview_id: id}, function(data) {
  //     var html = '<option value="0">必须选择时间段</option>';
  //     for (var i = 0; i < data.length; i++) {
  //       html += '<option value="' + data[i].starttime + '--' + data[i].end_time + '  ' + data[i].num + '">' + data[i].starttime + "--" + data[i].end_time + "</option>";
  //     }
  //     $("#interview_time").html(html);
  //     form.render("select");
  //   });
    
  //   form.on("select(interview_time)", function(data) {
  //     console.log(data.value);
  //     console.log(data)
  //     console.log(data.value.substring(0,16));
  //     console.log(data.value.substring(19,34));
  //     starttime = data.value.substring(0, 16);
  //     end_time = data.value.substring(18, 34);
  //     msrs = data.value.substring(35);
  //     $("#xg-subject-start").val(starttime);
  //     $("#xg-subject-end").val(end_time);
  //     $('.fade-xg-interview-subject-time input[name="student-number"]').val(msrs);
  //   });
  //   $('.fade-xg-interview-subject-time input[name="interview-subject"]').val(kmname);
  //   $('.fade-xg-interview-subject-time input[name="interview-subject"]').attr('data-index', id);
  //   $('.fade-xg-interview-subject-time').fadeIn(300);
  // })
  //  取消
  $('.fade-btn-interview-subject-del').on('click', function () {
    $('.fade-xg-interview-subject-time').fadeOut(300);
  })
  //确定
  // $('.fade-btn-interview-subject').on('click', function () {
  //   var id = $('.fade-xg-interview-subject-time input[name="interview-subject"]').attr('data-index');
  //   var num = $('.fade-xg-interview-subject-time input[name="student-number"]').val();
  //   var starttime = $('#xg-subject-start').val();
  //   var end_time = $('#xg-subject-end').val();
  //   $.post('pjsys/updateInterview_timeslot.action', {
  //     id: id,
  //     num: num,
  //     starttime: starttime,
  //     end_time: end_time
  //   }, function (data) {
  //     if (data === 1) {
  //       layer.msg('修改成功', {
  //         time: 1000
  //       }, function () {
  //         $('.fade-xg-interview-subject-time').fadeOut(300);
  //         $('.nav-xkgl').click()
  //       });
  //     } else {
  //       layer.msg('修改失败');
  //     }
  //   });
  // })
  // 面试场次
  $('.msxk-span3').on('click', function () {
    $.post('pjsys/findAllInterview.action', function (data) {
      var html = '<option value="0">选择科目</option>';
      for (var i = 0; i < data.length; i++) {
        html += '<option value="' + data[i].id + '">' + data[i].interviewSubject + '</option>'
      }
      $('#smkm_select').html(html)
      form.render("select")
    })
    $('.fade-field').show();
  })
  $('.fade-subject-time-btn-del').on('click', function () {
    $('.fade-field').hide();
  })
  $('.fade-subject-time-btn').on('click', function () {
    var kmid = $('#smkm_select').val();
    var startdate = $('#subject-start').val();
    var enddate = $('#subject-end').val();
    var num = $('.fade-field .fade-center-field .layui-form-item .layui-input-block input[name="student-number"]').val();
    if (kmid == 0) {
      layer.msg('请选择科目')
      return false;
    }
    if (startdate == '') {
      layer.msg('请选择开始时间')
      return false;
    }
    if (enddate == '') {
      layer.msg('请选择结束时间')
      return false;
    }
    if (num == '') {
      layer.msg('请输入人数')
      return false;
    }
    $.post('/pjsys/addInterview_timeslot.action', {
      interview_id: kmid,
      starttime: startdate,
      end_time: enddate,
      num: num
    }, function (data) {
      if (data === 1) {
        layer.msg('新增成功', {
          time: 1000
        }, function () {
          $('.fade-field').hide();
          $('.nav-kmgl').click()
        });
      } else {
        layer.msg('新增失败');
      }
    });
  })



  // 面试场次详情
  $('body').on('click', '.r-jw-xkgl .r-table .btn-interview-detail',function(e){
    e.stopPropagation()
    $('.fade-interview-detail').show();
    interview_id = $(this).attr('index_id');
    var html;
    $.post('/pjsys/queryInterviewtimeslotActionByinterviewid', { interview_id: interview_id},function(data){
      if(data==''){
        html+='<tr class="r-tbody-tr"><td colspan="4">暂无面试场次,请先添加面试场次</td></tr>'
      }else{
      for(var i=0;i<data.length;i++){
        html += '<tr class="r-tbody-tr"><td>' + data[i].starttime + '</td><td>' + data[i].end_time + '</td><td>' + data[i].num + '</td><td><span class="xg-bg btn_interview_xg" index_id="' + data[i].id + '" start="' + data[i].starttime + '" end = "' + data[i].end_time+'" num = "'+data[i].num+'" km="'+data[i].interview.interviewSubject+'">修改场次</span></td></tr>'
      }
      }
      $(".fade-interview-detail .fade-center .r-tbody").html(html);
    })
  })
  // 确认
  $('.fade-interview-xg').click(function () {
    $('.fade-interview-detail').hide()
  })
  //面试时间段详情
  $("body").on("click", " .fade-center .r-tbody .btn_interview_xg",function(e){
    $("#xg-subject-start").val($(this).attr('start'));
    $("#xg-subject-end").val($(this).attr('end'));
    $('.fade-xg-interview-subject-time input[name="student-number"]').val($(this).attr('num'));
    $('.fade-xg-interview-subject-time input[name="interview-subject"]').val($(this).attr('km'));
    $('.fade-xg-interview-subject-time input[name="interview-subject"]').attr('index_id',$(this).attr('index_id'));
    e.stopPropagation()
    $(".fade-interview-detail").hide();
    $(".fade-xg-interview-subject-time").show();
  });
  $('body').on('click','.fade-xg-interview-subject-time .fade-btn-interview-subject',function(){
    var id = $('.fade-xg-interview-subject-time input[name="interview-subject"]').attr('index_id');
    var start = $("#xg-subject-start").val();
    var end = $("#xg-subject-end").val();
    var num = $('.fade-xg-interview-subject-time input[name="student-number"]').val();
    $.post('/pjsys/updateInterviewSubject.action',{
      // id:id,
      interview_timeslot_id: id,
      num:num,
      end_time:end,
      starttime:start
    },function(data){
      if(data===1){
        layer.msg('修改成功',{time:2000})
        $('.fade-xg-interview-subject-time').fadeOut(300)
      }else{
        layer.msg('修改失败')
      }
    })
  })
  //----------面试科目管理结束----------

  //----------科目管理开始----------

  //------点击新增按钮出现科目管理新增页面
  $('.nav-xkgl').on('click', function (e) {
    $.post('pjsys/findAllSubject.action', function (data) {
      if (data) {
        var html;
        var interview;
        // data[i].interview 是否面试
        for (var i = 0; i < data.length; i++) {
          if (data[i].interview == 1) {
            interview = '是'
          } else {
            interview = '否'
          }
          if (user_role == 1 || user_role == 4) {
            if(data[i].interview == 1){
              html += '<tr class="r-tbody-tr">\n' +
              '                        <td>' + data[i].subject + '</td>\n' +
              '                        <td>' + interview + '</td>\n' +
              '                        <td>\n' +
              '                            <span class="xg-bg btn-xkgl-xg" data-id="' + data[i].id + '" data-interview="' + data[i].interview + '" data-introduction="' + data[i].introduction + '" data-majors-id="' + data[i].majors.id + '" >修改</span>\n' +
              '                            <span class="xg-bg btn-interview-detail" index_id="' + data[i].interview_id + '" index_name="' + data[i].subject + '">场次详情</span>\n' +
              '                            <span class="del-bg btn-xkgl-del"  data-id="' + data[i].id + '"  data-interview="' + data[i].interview + '" data-introduction="' + data[i].introduction + '"  data-majors-id="' + data[i].majors.id + '"  >删除</span>\n' +
              '                        </td>\n' +
              '                    </tr>'
            }
            else{
              html += '<tr class="r-tbody-tr">\n' +
              '                        <td>' + data[i].subject + '</td>\n' +
              '                        <td>' + interview + '</td>\n' +
              '                        <td>\n' +
              '                            <span class="xg-bg btn-xkgl-xg" data-id="' + data[i].id + '" data-interview="' + data[i].interview + '" data-introduction="' + data[i].introduction + '" data-majors-id="' + data[i].majors.id + '" >修改</span>\n' +
              '                            <span class="del-bg btn-xkgl-del"  data-id="' + data[i].id + '"  data-interview="' + data[i].interview + '" data-introduction="' + data[i].introduction + '"  data-majors-id="' + data[i].majors.id + '"  >删除</span>\n' +
              '                        </td>\n' +
              '                    </tr>'
            }
          }
          else {
            html += '<tr class="r-tbody-tr">\n' +
              '                        <td>' + data[i].subject + '</td>\n' +
              '                        <td>' + interview + '</td>\n' +
              '                        <td>\n' +
              '                            <span class="xg-bgs" data-id="' + data[i].id + '" data-interview="' + data[i].interview + '" data-introduction="' + data[i].introduction + '" data-majors-id="' + data[i].majors.id + '" >修改</span>\n' +
              '                            <span class="xg-bgs"  data-id="' + data[i].id + '"  data-interview="' + data[i].interview + '" data-introduction="' + data[i].introduction + '"  data-majors-id="' + data[i].majors.id + '"  >删除</span>\n' +
              '                        </td>\n' +
              '                    </tr>'
          }
        }
        $('.r-jw-xkgl .r-table .r-tbody-xkgl').html(html)
      }
    })
    $('.r-jw-xkgl').show().siblings().hide();
    e.stopPropagation()
  })
  $('.r-xkgl-xzxk').click(function () {
    $.post('pjsys/findAllMajors.action', function (data) {
      var html = '<option value="0"></option>';
      for (var i = 0; i < data.length; i++) {
        html += '<option value="' + data[i].id + '">' + data[i].major + '</option>'
      }
      $('.fade-xzxk .fade-xkgl-center select[name="major_id"]').html(html)
      $('.fade-xgxk .fade-xkgl-center .fade-center-left select[name="major_id"]').html(html)
      form.render("select")
    })
    $('.fade-xzxk').show()
  })
  //-----点击新增学科遮罩层的新增按钮
  $('.fade-xkgl-btn').click(function () {
    var major_id = $('.fade-xzxk .fade-xkgl-center select[name="major_id"]').val();
    var subject = $('.fade-xzxk .fade-xkgl-center input[name="title"]').val();
    var introduction = $('.fade-xzxk .fade-xkgl-center textarea').val();
    var interview = $('.fade-xzxk .fade-xkgl-center input:radio[name="xz-radio-interview-subject"]:checked').val();
    if (major_id == 0) {
      layer.msg('请选择学科');
      return false;
    }
    if (subject == '') {
      layer.msg('请填写科目名称');
      return false;
    }
    if (introduction == '') {
      layer.msg('请填写介绍');
      return false;
    }
    if (interview === undefined) {
      layer.msg('请选择是否面试');
      return false;
    }
    var num = 1
    if (interview == '不需要') {
      num = 0
    }
    $.post('pjsys/addSubject.action', {
      major_id: major_id,
      subject: subject,
      introduction: introduction,
      interview: num
    }, function (data) {
      if (data == 1) {
        layer.msg('增加科目成功', {
          time: 1000
        }, function () {
          $('.fade-xzxk .fade-xkgl-center input[name="title"]').val('');
          $('.fade-xzxk .fade-xkgl-center textarea').val('');
          $('.nav-xkgl').click()
        })
      } else {
        layer.msg('增加科目失败')
      }
    })
    $('.fade-xzxk').hide()
  })
  // -----点击新增学科遮罩层的取消按钮
  $('.fade-xkgl-btn-del').click(function () {
    $('.fade-xzxk').hide()
  });
  // ======学科管理修改和删除
  // -------修改
  $('body').on('click', '.r-jw-xkgl .r-tbody-xkgl .btn-xkgl-xg', function (e) {
    e.stopPropagation()
    var majors_id = $(this).attr('data-majors-id');
    var interview = $(this).attr('data-interview');
    var id = $(this).attr('data-id');
    $('.fade-xgxk .fade-xkgl-center .fade-center-left input[name="title"]').val($(this).parents('td').siblings('td').eq(0).text())
    $('.fade-xgxk .fade-xkgl-center .fade-center-left textarea').val($(this).attr('data-introduction'))
    $('.fade-xgxk .fade-xkgl-center .fade-center-left button').attr('data-id', id)
    if (interview == 1) {
      $('.fade-xgxk .fade-xkgl-center .fade-center-left input[type="radio"]').prop("checked", false)
      $('.fade-xgxk .fade-xkgl-center .fade-center-left input[type="radio"][title="需要"]').prop("checked", true)
      form.render()
    } else {
      $('.fade-xgxk .fade-xkgl-center .fade-center-left input[type="radio"]').prop("checked", false)
      $('.fade-xgxk .fade-xkgl-center .fade-center-left input[type="radio"][title="不需要"]').prop("checked", true)
      form.render()
    }
    $.post('pjsys/findAllMajors.action', function (data) {
      var html = '<option value="0"></option>';
      for (var i = 0; i < data.length; i++) {
        if (data[i].id == majors_id) {
          html += '<option value="' + data[i].id + '" selected="selected" >' + data[i].major + '</option>'
        } else {
          html += '<option value="' + data[i].id + '">' + data[i].major + '</option>'
        }
      }
      $('.fade-xgxk .fade-xkgl-center .fade-center-left select[name="major_id"]').html(html)
      form.render()
    })
    $('.fade-xgxk').fadeIn(300);
  })
  //-----学科管理之取消
  $('.fade-btn-xgxk-del').on('click', function (e) {
    e.stopPropagation()
    $('.fade-xgxk').fadeOut(300);
  })
  //-----学科管理之修改
  $('.fade-btn-xgxk').on('click', function (e) {
    e.stopPropagation()
    var major_id = $('.fade-xgxk .fade-xkgl-center .fade-center-left select[name="major_id"]').val()
    var subject = $('.fade-xgxk .fade-xkgl-center .fade-center-left input[name="title"]').val()
    var introduction = $('.fade-xgxk .fade-xkgl-center .fade-center-left textarea').val()
    var interview = $('.fade-xgxk .fade-xkgl-center .fade-center-left input[type="radio"]:checked').val();
    var interview_num = 0;
    var id= $(this).attr('data-id')
    if (interview == '需要') {
      interview_num = 1;
    }
    $.post(
      "pjsys/updateSubject.action",
      {
        id: id,
        major_id: major_id,
        subject: subject,
        introduction: introduction,
        interview: interview_num
      },
      function(data) {
        if (data === 1) {
          layer.msg(
            "修改成功",
            {
              time: 1000
            },
            function() {
              $(".nav-xkgl").click();
            }
          );
        } else {
          layer.msg("修改失败");
        }
      }
    );
    $('.fade-xgxk').fadeOut(300);
  })
  //-------删除
  $('body').on('click', '.r-jw-xkgl .r-tbody-xkgl .btn-xkgl-del', function (e) {
    e.stopPropagation()
    var id = $(this).attr('data-id')
    layer.confirm('亲！你真到要删除这条记录吗？', {
      btn: ['确定', '取消'] //按钮
    }, function () {
      $.post('pjsys/deleteSubject.action', {
        id: id}, function (data) {
        if (data === 1) {
          layer.msg('删除成功', {
            time: 1000
          }, function () {
            $('.nav-xkgl').click()
          });
        } else {
          layer.msg('删除失败');
        }
      })
    });
  })

  //----------科目管理结束----------
  //----------班级管理开始----------

  // ------班级管理之新增班级
  $('.r-bjgl-xz').on('click', function () {
    $.post('/pjsys/findAllSubject.action', function (data) {
      var option = '<option value=""></option>';
      for (var i = 0; i < data.length; i++) {
        option += '<option value="' + data[i].id + '">' + data[i].subject + '</option>'
      }
      $('#xz-select-class').html(option)
      form.render('select'); //这个很重要
    })
    $('.fade-xzbj').show();
  })
  // -----新增班级确定按钮
  $('.fade-xzbj-btn').click(function () {
    var subject_id = $('.fade-xzbj .fade-xzbj-center .fade-center-left .layui-form #xz-select-class').val();
    var classname = $('.fade-xzbj .fade-xzbj-center .fade-center-left .layui-form input[name="xz-class-name"]').val();
    var unit_price = $('.fade-xzbj .fade-xzbj-center .fade-center-left .layui-form input[name="xz-class-hour-cost"]').val();
    var min_age = $('.fade-xzbj .fade-xzbj-center .fade-center-left .layui-form input[name="xz-class-min-age"]').val();
    var max_age = $('.fade-xzbj .fade-xzbj-center .fade-center-left .layui-form input[name="xz-class-max-age"]').val();
    var keshi = $('.fade-xzbj .fade-xzbj-center .fade-center-left .layui-form input[name="xz-class-hour"]').val();
    var money = $('.fade-xzbj .fade-xzbj-center .fade-center-left .layui-form input[name="xz-total-class-hour-cost"]').val();
    var number = $('.fade-xzbj .fade-xzbj-center .fade-center-left .layui-form input[name="xz-class-student-number"]').val();
    var kaifang = $('.fade-xzbj .layui-form-item select[name="xz-kaifang"]').val()
    form.render("select")
    if (!subject_id) {
      layer.msg('请选择科目');
      return false;
    }
    if (classname == '') {
      layer.msg('班级名不能为空');
      return false;
    }
    if (unit_price == '') {
      layer.msg('报名费不能为空');
      return false;
    }
    if (min_age == '') {
      layer.msg('年龄下限不能为空');
      return false;
    }
    if (max_age == '') {
      layer.msg('年龄上限不能为空');
      return false;
    }
    if (keshi == '') {
      layer.msg('课时不能为空');
      return false;
    }
    if (money == '') {
      layer.msg('总课时费不能为空');
      return false;
    }
    if (number == '') {
      layer.msg('人数不能为空');
      return false;
    }
    $.post('/pjsys/addClassinfo.action', {
      subject_id: subject_id,
      classname: classname,
      unit_price: unit_price,
      max_age: max_age,
      min_age: min_age,
      keshi: keshi,
      money: money,
      number: number,
      kaifang: kaifang
    }, function (data) {
      if (data === 1) {
        layer.msg('新增班级成功', {
          time: 1000
        }, function () {
          $('.fade-xzbj').hide();
          $('.nav-bjgl').click()
          console.log(kaifang)
        });
      } else {
        layer.msg('新增班级失败');
      }
    })
  })
  // -----新增班级取消按钮
  $('.fade-xzbj-btn-del').click(function () {
    $('.fade-xzbj').hide();
  })
  // 班级管理-修改班级
  $('.r-jw-bjgl').on('click', '.r-bjgl-xgbj', function () {
    console.log($(this).attr('index-id'))
    var id = $(this).attr('index-id')
    // $.post('/pjsys/showclassinfo.action',{classname: name}, function(data) {
      // 得到班级详细信息
    $.post('/pjsys/showclassinfoId', { bid: id}, function(data) {
      // 科目
      $('.fade-xgbj .layui-form-item').eq(0).find('input').val(data[0].subject.subject)
      // 班级名
      $('.fade-xgbj .layui-form-item').eq(1).find('input').val(data[0].classname)
      // 报名费
      $('.fade-xgbj .layui-form-item').eq(2).find('input').val(data[0].unit_price)
      // 年龄下线
      $('.fade-xgbj .layui-form-item').eq(3).find('input').val(data[0].min_age)
      // 年龄上限
      $('.fade-xgbj .layui-form-item').eq(4).find('input').val(data[0].max_age)
      // 课时
      $('.fade-xgbj .layui-form-item').eq(5).find('input').val(data[0].keshi)
      // 总课时费
      $('.fade-xgbj .layui-form-item').eq(6).find('input').val(data[0].money)
      // 额定人数
      $('.fade-xgbj .layui-form-item').eq(7).find('input').val(data[0].number)
      // 开放
      if (data[0].kaifang == 1) {
        $('.fade-xgbj .layui-form-item input[type="radio"]').prop("checked", false)
        $('.fade-xgbj .layui-form-item input[type="radio"][title="开放"]').prop("checked", true)
        form.render()
      } else {
        $('.fade-xgbj .layui-form-item input[type="radio"]').prop("checked", false)
        $('.fade-xgbj .layui-form-item input[type="radio"][title="关闭"]').prop("checked", true)
        form.render()
      }
    })
    $('.fade-xgbj .fade-xgbj-btn').attr('subject_id', $(this).attr('subject_id'));
    $('.fade-xgbj .fade-xgbj-btn').attr('index-id', $(this).attr('index-id'))

    $('.fade-xgbj').show();
  })
  //修改班级提交
  $('.fade-xgbj-btn').click(function () {
    var id = $(this).attr('index-id');
    var classname = $('.fade-xgbj .layui-form-item').eq(1).find('input').val();
    var unit_price = $('.fade-xgbj .layui-form-item').eq(2).find('input').val();
    var min_age = $('.fade-xgbj .layui-form-item').eq(3).find('input').val();
    var max_age = $('.fade-xgbj .layui-form-item').eq(4).find('input').val();
    var keshi = $('.fade-xgbj .layui-form-item').eq(5).find('input').val();
    var money = $('.fade-xgbj .layui-form-item').eq(6).find('input').val();
    var number = $('.fade-xgbj .layui-form-item').eq(7).find('input').val();
    // var kaifang = $('.fade-xgbj .layui-form-item select[name="xg-kaifang"]').val()
    var kaifang = $('.fade-xgbj .layui-form-item input[type="radio"]:checked').val();
    form.render("select")
    var subject_id = $(this).attr('subject_id');
    $.post('/pjsys/updateClassinfo.action', {
      id: id,
      classname: classname,
      keshi: keshi,
      unit_price: unit_price,
      money: money,
      max_age: max_age,
      min_age: min_age,
      number: number,
      kaifang: kaifang
    }, function (data) {
      if (data == 1) {
        layer.msg('修改成功', {
          time: 1000
        }, function () {
          $('.fade-xgbj').hide();
          // 局部刷新
          if ($('.clsss-search').css('display') == 'none') {
            $('.nav-bjgl').click();
          } else {
            $('.clsss-search .r-wenzi .btn-class-search').click()
          }
        });
      } else {
        layer.msg('修改失败');
      }
    });
  })
  // 修改班级取消
  $('.fade-xgbj-btn-del').on('click', function () {
    $('.fade-xgbj').hide();
  })
  // 班级管理-删除班级
  $('.r-jw-bjgl').on('click', '.r-bjgl-del', function () {
    $.post('/pjsys/deleteClassinfo.action', {
      id: $(this).attr('index-id')
    }, function (data) {
      if (data == 1) {
        layer.msg('删除成功', {
          time: 1000
        }, function () {
          $('.nav-bjgl').click()
        });
      } else {
        layer.msg('删除失败');
      }
    })
  })
  // 所有班级
  // 班级管理之合并班级
  $('body').on('click', '.merge-class',function(e){
    var classname =''
    classname = $(this).parents('tr').find('td').eq(1).text();
    // 需要清除的班级
    // var classinfo_id_clear = $(this).attr('index-id');
    $('.fade-merge-class #fade-merge-unClass').attr('classinfo', $(this).attr('index-id'))
    $('body .fade-merge-class .fade-center .fade-class').val(classname)
    navkq('body .fade-merge-class .fade-center select[name="fade-merge-class"]')
    $('.fade-merge-class').show()
      e.stopPropagation()
  })
    $('body').on('click', '.fade-merge-class-btn', function (e) {
      var classinfo_id = ''
      var classinfo_id_clear = ''
        classinfo_id_clear = $('.fade-merge-class #fade-merge-unClass').attr('classinfo')
        classinfo_id = $('.fade-merge-class .fade-center select[name="fade-merge-class"]').val();
        // console.log($('.fade-merge-class .fade-center select[name="fade-merge-class"]').val())
        // console.log(classinfo_id)
        if (classinfo_id == '' || classinfo_id == null || classinfo_id == classinfo_id_clear){
          layer.msg('请仔细查看两个班级是否一致,或合并到的班级是否选择',{time:3000})
        } else {
          $.post('/pjsys/mergeClassinfo',{classinfo_id:classinfo_id, classinfo_id_clear: classinfo_id_clear},function(data) {
          if(data == 1){
            $('.fade-merge-class').hide();
            $('.nav-bjgl').click()
            layer.msg('您已成功合并当前班级', {
            time: 2000
            })
            classinfo_id = '', classinfo_id_clear =''
          }else{
            layer.msg('失败请重新尝试', {time: 2000})
          }
        })
        }
        // if (classinfo_id == '' || classinfo_id == null || classinfo_id == classinfo_id_clear){
        //   layer.msg('请仔细查看两个班级是否一致,或合并到的班级是否选择',{time:3000})
        // } else if (classinfo_id != '' && classinfo_id != null || classinfo_id != classinfo_id_clear){
        //             $.post('/pjsys/mergeClassinfo',{classinfo_id:classinfo_id, classinfo_id_clear: classinfo_id_clear},function(data) {
        //             if(data == 1){
        //               $('.fade-merge-class').hide();
        //               $('.nav-bjgl').click()
        //               layer.msg('您已成功合并当前班级', {
        //               time: 2000
        //               })
        //             }else{
        //               layer.msg('失败请重新尝试', {time: 2000})
        //             }
        //           })
        // }
        e.stopPropagation()
      })
  $('body').on('click', '.fade-merge-class-btn-del', function () {
    $('.fade-merge-class').hide()
  })
  // 升级班级
  $('.r-bjgl-sj').click(function(){
    layer.open({
    type: 1,
    title: '升级班级操作提示',
    skin: 'layui-layer-molv',
    area: ['420px', '150px'],
    content: '&emsp;&emsp;1.请先创建一个空的班级(升级过后的)</br>&emsp;&emsp;2.请点击需要升级的班级后面的合并按钮</br>&emsp;&emsp;3.选择刚刚创建好的升级班级,然后点击确定即可'
  });
  })
  // 未分配班级
  $('.r-bjgl-fp').click(function(){
    $.post('/pjsys/classinfoInclassroominfo_idIsNull',function(data){
      var content = ''
      for(var i=0; i<data.length;i++){
        content += data[i].classname+'</br>'
      }
      layer.open({
        type: 1,
        title: '未分配班级',
        skin: 'layui-layer-molv',
        area: ['420px', '150px'],
        content: '&emsp;&emsp;'+content
        });
    })
  })
  // 班级缴费状态
  $("body").on("click", ".r-jw-bjgl .r-bjgl-xf",function (e) { 
    $(".fade-xf").show();
    $.post("/pjsys/findAllClassinfos.action",function(data){
      var html ='';
      for(var i=0;i<data.length;i++){
        html += '<tr class="r-tbody-tr" style="height: 30px;"><td><input type="checkbox" name="" ids="'+data[i].id+'"></td><td>'+data[i].classname+'</td><tr>';
      }
      $("body .fade-xf .r-tbody").html(html);
    });
    e.stopPropagation()
  });
  $('body').on('click','.fade-xf-btn',function(data){
    var checked = [];
    for (var i = 0; i < $('.fade-xf .r-table .r-tbody .r-tbody-tr td input[type="checkbox"]').length;i++){
      if ($('.fade-xf .r-table .r-tbody .r-tbody-tr td input[type="checkbox"]').eq(i).is(':checked')) {
        checked.push($('.fade-xf .r-table .r-tbody .r-tbody-tr td input[type="checkbox"]').eq(i).attr('ids'))
      }
    }
    checked = checked.join(",");
    $.post("pjsys/infoUpdateClassinfo_idsByflag", {
      classinfo_ids: checked,
      my_flag: 7
    },function(data){
      if(data === 1){
        layer.msg('成功')
      }else{
        layer.msg('失败')
      }
    });
  })
  $("body").on("click", ".fade-xf-btn-del",function(){
    $(".fade-xf").hide();
  });
  $('body').on('click', '.fade-xf .r-table .r-thead .r-thead-tr th input[type="checkbox"]', function () {
    $('.fade-xf .r-table .r-tbody .r-tbody-tr td input[type="checkbox"]').prop("checked", $(this).prop("checked"));
  });
  $('body').on('click', '.fade-xf .r-table .r-tbody td input[type="checkbox"]', function () {
    $(this).prop("checked", $(this).prop("checked"));
  });
  // 班级管理之列表
  $('.nav-bjgl').on('click', function (e) {
    $.post('pjsys/findAllClassinfo.action', {
      page: 1
    }, function (data) {
      first_total = data.total;
      $('.r-jw-bjgl .clsss-old .r-tbody-bjgl').html('');
      laypage.render({
        elem: 'class-search'
          ,
        count: first_total
          ,
        theme: "#65daf7",
        first: '首页',
        last: '尾页',
        jump: function (obj, first) {
          $.post('pjsys/findAllClassinfo.action', {
            page: obj.curr
          }, function (data) {
            var frxx_form_hmtl = '';
            for (var i = 0; i < data.datas.length; i++) {
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
              var kaifang = '';
              if (data.datas[i].kaifang === 1) {
                kaifang = '开放'
              } else if (data.datas[i].kaifang === 0) {
                kaifang = '关闭'
              }
                frxx_form_hmtl += '<tr class="r-tbody-tr" ><td>' + data.datas[i].subject.subject + '</td><td>' + data.datas[i].classname + '</td><td>' + data.datas[i].classroom.name + '</td><td>' + data.datas[i].keshi + '</td><td>' + data.datas[i].money + '</td> <td>' + data.datas[i].number + '</td><td>' + data.datas[i].count + '</td><td>' + data.datas[i].num + '</td><td>'+ kaifang+ '</td><td>' + day + ' ' + data.datas[i].classroominfo.start + '--' + data.datas[i].classroominfo.jieshu + '</td><td>' + data.datas[i].min_age + '</td><td><span class="xg-bg r-bjgl-xgbj"  subject_id="' + data.datas[i].subject.id + '" index-id="' + data.datas[i].id + '" >修改</span><span class="xg-bg merge-class"  index-id="' + data.datas[i].id + '" >合并</span><span class="xg-bg r-bjgl-student" subject_id="' + data.datas[i].subject.id + '" index-id="' + data.datas[i].id + '" >添加</span><span class="del-bg r-bjgl-del"  index-id="' + data.datas[i].id + '" >删除</span></tr>';
            }
            $('.r-jw-bjgl .clsss-old .r-tbody-bjgl').html(frxx_form_hmtl);
          })
        }
      });
    })
    $('.r-jw-bjgl').show().siblings().hide();
    e.stopPropagation()
  })
  // 导出班级模板
  $('.export-template').click(function(){
    window.location.href = '/pjsys/downloadStudent'
  })
  // 导入班级模板
      var uploadInst = upload.render({
        elem: '.import-template',
        url: '/pjsys/loadStudentExcel',
        data: {
          classinfo_id: function () {
            return $('.import-template').attr('index-id')
          }
        },
        accept: 'file',
        exts: 'xls',
        before: function () {
          layer.load();
        },
        done: function (res) {
          layer.closeAll('loading');
          var html = ''
          var ids = $('.import-template').attr('index-id')
          for(var i=0;i<res.length;i++){
            // html += res[i].new_stuName--- res.flag_info
            if(res[i].flag != 1){
              html += '<div class="layui-form"><table class = "layui-table"> <tr> <tbody><td>' + res[i].new_stuName + '</td><td>' + res[i].flag_info + '</td></tbody></tr></table></div>'
            }
          }
          if(html!=''){
          layer.msg('成功', {time: 1000},function(){
            // 刷新学生列表
            class_stu_name(ids)
            layer.open({
              type: 1,
              skin: 'layui-layer-demo', //样式类名
              closeBtn: 0, //不显示关闭按钮
              anim: 2,
              shadeClose: true, //开启遮罩关闭
              content: html
            });
          })
          }else{
            layer.msg('成功', {time: 1000})
            class_stu_name(ids)
          }
        },
        error: function () {
          layer.closeAll('loading');
          layer.msg('失败重新尝试')
        }
      });
  // }

  // 搜索班级
  $('.clsss-old .btn-class-search').on('click', function () {
    $('.clsss-old').hide();
    $('.clsss-search').show();
  })
  $('.clsss-search .r-wenzi .btn-class-search').on('click', function () {
    //  $('.r-jw-bjgl .clsss-search .r-tbody-bjgl').html(frxx_form_hmtl);
    if ($('#find-class').val() == '') {
      layer.msg('请输入班级名');
      return false;
    }
    $.post('/pjsys/showclassinfo.action', {
      classname: $('#find-class').val()
    }, function (data) {
      $('.clsss-search .r-tbody-bjgl').html('');
      var frxx_form_hmtl = '';
      for (var i = 0; i < data.length; i++) {
        var day = '';
        if (data[i].classroominfo.day === 1) {
          day = '星期一'
        } else if (data[i].classroominfo.day === 2) {
          day = '星期二'
        } else if (data[i].classroominfo.day === 3) {
          day = '星期三'
        } else if (data[i].classroominfo.day === 4) {
          day = '星期四'
        } else if (data[i].classroominfo.day === 5) {
          day = '星期五'
        } else if (data[i].classroominfo.day === 6) {
          day = '星期六'
        } else if (data[i].classroominfo.day === 7) {
          day = '星期日'
        }
        var kaifang = '';
        if (data[i].kaifang === 1) {
          kaifang = '开放'
        } else if (data[i].kaifang === 0) {
          kaifang = '关闭'
        }
        frxx_form_hmtl += '<tr class="r-tbody-tr"><td>' + data[i].subject.subject + '</td><td>' + data[i].classname + '</td><td>' + data[i].classroom.name + '</td><td>' + data[i].keshi + '</td><td>' + data[i].money + '</td><td>' + data[i].number + '</td><td>' + data[i].count + '</td><td>' + data[i].num + '</td><td>'+ kaifang + '</td><td>' + day + ' ' + data[i].classroominfo.start + '--' + data[i].classroominfo.jieshu + '</td><td>' + data[i].min_age + '</td><td><span class="xg-bg r-bjgl-xgbj" subject_id="' + data[i].subject.id + '" index-id="' + data[i].id + '" >修改</span><span class="xg-bg merge-class" index-id="' + data[i].id + '">合并</span><span class="xg-bg r-bjgl-student" subject_id="' + data[i].subject.id + '"  index-id="' + data[i].id + '" >添加</span><span class="del-bg r-bjgl-del"  index-id="' + data[i].id + '" >删除</span></td></tr>';
      }
      $('.r-jw-bjgl .clsss-search .r-tbody-bjgl').html(frxx_form_hmtl);
    })
  })
  //  返回搜索
  $('.btn-class-return').on('click', function () {
    $('.nav-bjgl').click()
    $('.clsss-search').hide();
    $('.clsss-old').show();
  })
  // 班级管理清空学生
  $(".r-jw-bjgl-add-student .emptyStu").on('click',function(){
    var classinfo_id = $(this).attr('index-id');
    console.log(classinfo_id);
    $.post("/pjsys/classinfoClearStudent", { classinfo_id: classinfo_id },function(data){
      if(data ===1 ) {
        layer.msg('已经清空学生')
      }else{
        layer.msg('失败')
      }
    });
  });


  //班级管理添加学生
  var add_xs_num = 5;
  var bj_index;
  $('.r-jw-bjgl').on('click', '.r-bjgl-student', function () {
    bj_index = $(this).attr('index-id');
    $('.r-jw-bjgl-add-student .r-wenzi span').eq(1).text($(this).parents('.r-tbody-tr').find('td').eq(1).html())
    $('.r-jw-bjgl-add-student .r-wenzi span').eq(1).attr('index-id', $(this).attr('index-id'));
    $('.r-jw-bjgl-add-student .r-wenzi button').eq(2).attr('index-id', $(this).attr('index-id'));
    $('.r-jw-bjgl-add-student .r-wenzi button').eq(3).attr('index-id', $(this).attr('index-id'));
    $('.r-jw-bjgl-add-student .r-wenzi button').eq(4).attr('index-id', $(this).attr('index-id'));
    $('.r-jw-bjgl-add-student .r-wenzi button').eq(5).attr('index-id', $(this).attr('index-id'));
    class_stu_name(bj_index)
    $.post('/pjsys/CountStudentByClassInfoId.action', {classinfo_id: bj_index}, function (data) {
      $('.r-jw-bjgl-add-student .r-wenzi span').eq(3).text(data)
    });
    $('.r-jw-bjgl').hide();
    $('.r-jw-bjgl-add-student').show();
  })
  function class_stu_name (id) {
    $.post('/pjsys/findByClassinfoId.action', {classinfo_id: id,page: 1}, function (data) {
      add_xs_num = data.total;
      if (data.datas == null) {
        $('.r-jw-bjgl-add-student .r-tbody').html('<tr class="r-tbody-tr"><td colspan="6">暂无学生数据</td></tr>');
        return false;
      } else {
        laypage.render({
          elem: 'class-student-add-page',
          count: add_xs_num,
          theme: "#65daf7",
          jump: function (obj, first) {
            $.post('/pjsys/findByClassinfoId.action', {
              classinfo_id: bj_index,
              page: obj.curr
            }, function (data) {
              $('.r-jw-bjgl-add-student .r-tbody').html('');
              bjlst_page = obj.curr;
              first_page = obj.first;
              var html;
              if (data.datas == null) {
                $('.r-jw-bjgl-add-student .r-tbody').html('<tr class="r-tbody-tr"><td colspan="6">暂无学生数据</td></tr>');
                return false;
              } else {
                for (var i = 0; i < data.datas.length; i++) {
                  var sex;
                  var flag;
                  var flags;
                  if (data.datas[i].student.sex == 1) {
                    sex = '男'
                  } else {
                    sex = '女'
                  }
                  if (data.datas[i].flag === 1) {
                    flags = '已报名未缴费';
                  } else if (data.datas[i].flag === 2) {
                    flags = '已申请缴费,未缴费';
                  } else if (data.datas[i].flag === 3) {
                    flags = '已缴费';
                  } else if (data.datas[i].flag === 4) {
                    flags = '已退班';
                  } else if (data.datas[i].flag === 5) {
                    flags = '已申请退费';
                  } else if (data.datas[i].flag === 6) {
                    flags = '已成功退费';
                  } else if (data.datas[i].flag === 7) {
                    flags = '未申请续费';
                  } else if (data.datas[i].flag === 8) {
                    flags = '已申请续费';
                  } else if (data.datas[i].flag === 9) {
                    flags = '已成功续费';
                  } else if (data.datas[i].flag === 10) {
                    flags = '已拒绝缴费';
                  } else if (data.datas[i].flag === 11) {
                    flags = '已拒绝续费';
                  } else if (data.datas[i].flag === 12) {
                    flags = '已拒绝缴费';
                  }
                  if (data.datas[i].flags == null) {
                    flag = '未备注'
                  } else {
                    flag = data.datas[i].flags
                  }

                  html += '<tr class="r-tbody-tr">\n' +
                    '                        <td>' + data.datas[i].student.stuName + '</td>\n' +
                    '                        <td>' + sex + '</td>\n' +
                    '                        <td>' + data.datas[i].student.personalNum + '</td>\n' +
                    '                        <td>' + flags + '</td>\n' +
                    '                        <td>' + flag + '</td>\n' +
                    '                        <td>\n' +
                    '                            <span class="del-bg btn-class-student" index-id="' + data.datas[i].id + '">删除</span>\n' +
                    '                        </td>\n' +
                    '                    </tr>'
                }
                $('.r-jw-bjgl-add-student .r-tbody').html(html);
              }
            })
          }
        });
      }
    });
  }
  // 返回
  $('.bjgl-return').on('click', function () {
    $('.r-jw-bjgl-add-student').hide();
    $('.r-jw-bjgl').show();
  })
  // 倒序查看学生
  $('body').on('click', '.r-jw-bjgl-add-student .bjgl-daoxu',function(){
    $.post('pjsys/findByDesc.action', {
      classinfo_id: $('.r-jw-bjgl-add-student .r-wenzi span').eq(1).attr('index-id'),
      page: 1
    },function(data){
        add_xs_num = data.total;
        var html;
        if (data.datas == null) {
          $('.r-jw-bjgl-add-student .r-tbody').html('<tr class="r-tbody-tr"><td colspan="4">暂无数据</td></tr>');
          return false;
        } else {
          laypage.render({
            elem: 'class-student-add-page'
              ,
            count: add_xs_num
              ,
            theme: "#65daf7",
            jump: function (obj, first) {
              $.post('pjsys/findByDesc.action', {
                classinfo_id: bj_index,
                page: obj.curr
              }, function (data) {
                $('.r-jw-bjgl-add-student .r-tbody').html('');
                bjlst_page = obj.curr;
                first_page = obj.first;
                var html;
                var flags;
                if (data.datas == null) {
                  $('.r-jw-bjgl-add-student .r-tbody').html('<tr class="r-tbody-tr"><td colspan="4">暂无数据</td></tr>');
                  return false;
                } else {
                  for (var i = 0; i < data.datas.length; i++) {
                    var sex;
                    var flag;
                    if (data.datas[i].student.sex == 1) {
                      sex = '男'
                    } else {
                      sex = '女'
                    }
                    if (data.datas[i].flags == null) {
                      flag = '未备注'
                    } else {
                      flag = data.datas[i].flags
                    }
                  if (data.datas[i].flag === 1) {
                    flags = '已报名未缴费';
                  } else if (data.datas[i].flag === 2) {
                    flags = '已申请缴费,未缴费';
                  } else if (data.datas[i].flag === 3) {
                    flags = '已缴费';
                  } else if (data.datas[i].flag === 4) {
                    flags = '已退班';
                  } else if (data.datas[i].flag === 5) {
                    flags = '已申请退费';
                  } else if (data.datas[i].flag === 6) {
                    flags = '已成功退费';
                  } else if (data.datas[i].flag === 7) {
                    flags = '未申请续费';
                  } else if (data.datas[i].flag === 8) {
                    flags = '已申请续费';
                  } else if (data.datas[i].flag === 9) {
                    flags = '已成功续费';
                  } else if (data.datas[i].flag === 10) {
                    flags = '已拒绝缴费';
                  } else if (data.datas[i].flag === 11) {
                    flags = '已拒绝续费';
                  } else if (data.datas[i].flag === 12) {
                    flags = '已拒绝退费';
                  }
                    html += '<tr class="r-tbody-tr">\n' +
                      '                        <td>' + data.datas[i].student.stuName + '</td>\n' +
                      '                        <td>' + sex + '</td>\n' +
                      '                        <td>' + data.datas[i].student.personalNum + '</td>\n' +
                      '                        <td>' + flags + '</td>\n' +
                      '                        <td>' + flag + '</td>\n' +
                      '                        <td>\n' +
                      '                            <span class="del-bg btn-class-student" index-id="' + data.datas[i].id + '">删除</span>\n' +
                      '                        </td>\n' +
                      '                    </tr>'
                  }
                  $('.r-jw-bjgl-add-student .r-tbody').html(html);
                }
              })
            }
          });
        }
    })
  })
  //删除
  $('body').on('click', '.r-jw-bjgl-add-student .btn-class-student', function () {
    $.post('pjsys/deleteById.action', {id: $(this).attr('index-id')}, function (data) {
      if (data == 1) {
        layer.msg('删除成功', {
          time: 1000
        }, function () {
          $('.r-jw-bjgl-add-student .r-wenzi span').eq(1).text($(this).parents('.r-tbody-tr').find('td').eq(1).html())
          $('.r-jw-bjgl-add-student .r-wenzi span').eq(1).attr('index-id', $(this).attr('index-id'));
          class_stu_name(bj_index)
          // $.post('/pjsys/findByClassinfoId.action', {
          //   classinfo_id: bj_index,
          //   page: 1
          // }, function (data) {
          //   add_xs_num = data.total;
          //   var html;
          //   if (data.datas == null) {
          //     $('.r-jw-bjgl-add-student .r-tbody').html('<tr class="r-tbody-tr"><td colspan="4">暂无数据</td></tr>');
          //     return false;
          //   } else {
          //     laypage.render({
          //       elem: 'class-student-add-page'
          //         ,
          //       count: add_xs_num
          //         ,
          //       theme: "#65daf7",
          //       jump: function (obj, first) {
          //         $.post('/pjsys/findByClassinfoId.action', {
          //           classinfo_id: bj_index,
          //           page: obj.curr
          //         }, function (data) {
          //           $('.r-jw-bjgl-add-student .r-tbody').html('');
          //           bjlst_page = obj.curr;
          //           first_page = obj.first;
          //           var html;
          //           if (data.datas == null) {
          //             $('.r-jw-bjgl-add-student .r-tbody').html('<tr class="r-tbody-tr"><td colspan="4">暂无数据</td></tr>');
          //             return false;
          //           } else {
          //             for (var i = 0; i < data.datas.length; i++) {
          //               var sex;
          //               var flag;
          //               if (data.datas[i].student.sex == 1) {
          //                 sex = '男'
          //               } else {
          //                 sex = '女'
          //               }
          //               if (data.datas[i].flags == null) {
          //                 flag = '未备注'
          //               } else {
          //                 flag = data.datas[i].flags
          //               }

          //               html += '<tr class="r-tbody-tr">\n' +
          //                 '                        <td>' + data.datas[i].student.stuName + '</td>\n' +
          //                 '                        <td>' + sex + '</td>\n' +
          //                 '                        <td>' + data.datas[i].student.personalNum + '</td>\n' +
          //                 '                        <td>' + flag + '</td>\n' +
          //                 '                        <td>\n' +
          //                 '                            <span class="del-bg btn-class-student" index-id="' + data.datas[i].id + '">删除</span>\n' +
          //                 '                        </td>\n' +
          //                 '                    </tr>'
          //             }
          //             $('.r-jw-bjgl-add-student .r-tbody').html(html);
          //           }
          //         })
          //       }
          //     });
          //   }
          // })
        });
      } else {
        layer.msg('删除失败');
      }
    })
  })
  //按姓名查找学生
  $('.bjgl-add-student').on('click', function () {
    $('.fade-search-add-student').show();
  })
  //按照姓名查找
  $('.btn-add-student-search').on('click', function () {
    var text = $('.fade-search-add-student input[name="class-name"]').val();
    if (text == '') {
      $('.fade-search-add-student .fade-center .r-tbody').html('')
      layer.msg('名字不能为空')
      return false;
    }
    $.post('/pjsys/show.action', {
      stuName: text
    }, function (data) {
      if (data == false) {
        $('.fade-search-add-student .fade-center .r-tbody').html('<tr class="r-tbody-tr"><td colspan="4">没有查询到匹配数据</td></tr>')
      } else {
        var html;
        for (var i = 0; i < data.length; i++) {
          var sex;
          if (data[i].sex == 1) {
            sex = '男'
          } else {
            sex = '女'
          }
          html += '<tr class="r-tbody-tr" xsid="' + data[i].id + '" >\n' +
            '                                        <td>\n' +
            '                                            <input type="checkbox" xsid="' + data[i].id + '" name="" style="display: inline-block" >\n' +
            '                                        </td>\n' +
            '                                        <td>' + data[i].stuName + '</td>\n' +
            '                                        <td>' + sex + '</td>\n' +
            '                                        <td>' + data[i].personalNum + '</td>\n' +
            '                                    </tr>'
        }
        $('.fade-search-add-student .fade-center .r-tbody').html(html)
      }
    })
  })
  //取消
  $('.btn-add-student-del').on('click', function () {
    $('.fade-search-add-student .layui-form-item .layui-input-block input[type="text"]').val('')
    $('.fade-search-add-student .fade-center .r-tbody').html('')
    $('.fade-search-add-student').hide();
  })
  //新加
  $('.btn-add-student').on('click', function () {
    var banjiid = $('.r-jw-bjgl-add-student .r-wenzi span').eq(1).attr('index-id');
    var checked = [];
    for (var i = 0; i < $('.fade-search-add-student .r-tbody .r-tbody-tr').length; i++) {
      if ($('.fade-search-add-student .r-tbody .r-tbody-tr').eq(i).find('td').eq(0).find('input').prop("checked")) {
        checked.push($('.fade-search-add-student .r-tbody .r-tbody-tr').eq(i).attr('xsid'));
      }
    }
    checked = checked.join(',');
    $.post('/pjsys/addInfo.action', {
      classinfo_id: banjiid,
      stu_id: checked
    }, function (data) {
      // console.log(data);
      if (data == 1) {
        layer.msg('添加成功,请继续添加', {
          time: 1000
        }, function () {
          class_stu_name(banjiid)
          // $('.fade-search-add-student').hide();
          // $.post('/pjsys/findByClassinfoId.action', {
          //   classinfo_id: bj_index,
          //   page: 1
          // }, function (data) {
          //   add_xs_num = data.total;
          //   var html;
          //   if (data.datas == null) {
          //     $('.r-jw-bjgl-add-student .r-tbody').html('<tr class="r-tbody-tr"><td colspan="4">暂无数据</td></tr>');
          //     return false;
          //   } else {
          //     laypage.render({
          //       elem: 'class-student-add-page'
          //         ,
          //       count: add_xs_num
          //         ,
          //       theme: "#65daf7",
          //       jump: function (obj, first) {
          //         $.post('/pjsys/findByClassinfoId.action', {
          //           classinfo_id: bj_index,
          //           page: obj.curr
          //         }, function (data) {
          //           $('.r-jw-bjgl-add-student .r-tbody').html('');
          //           bjlst_page = obj.curr;
          //           first_page = obj.first;
          //           var html;
          //           if (data.datas == null) {
          //             $('.r-jw-bjgl-add-student .r-tbody').html('<tr class="r-tbody-tr"><td colspan="6">暂无数据</td></tr>');
          //             return false;
          //           } else {
          //             for (var i = 0; i < data.datas.length; i++) {
          //               var sex;
          //               var flag;
          //               if (data.datas[i].student.sex == 1) {
          //                 sex = '男'
          //               } else {
          //                 sex = '女'
          //               }
          //               if (data.datas[i].flags == null) {
          //                 flag = '未备注'
          //               } else {
          //                 flag = data.datas[i].flags
          //               }
          //               html += '<tr class="r-tbody-tr">\n' +
          //                 '                        <td>' + data.datas[i].student.stuName + '</td>\n' +
          //                 '                        <td>' + sex + '</td>\n' +
          //                 '                        <td>' + data.datas[i].student.personalNum + '</td>\n' +
          //                 '                        <td>' + flag + '</td>\n' +
          //                 '                        <td>\n' +
          //                 '                            <span class="del-bg btn-class-student" index-id="' + data.datas[i].id + '">删除</span>\n' +
          //                 '                        </td>\n' +
          //                 '                    </tr>'
          //             }
          //             $('.fade-search-add-student .layui-form-item .layui-input-block input[type="text"]').val('')
          //             $('.fade-search-add-student .fade-center .r-tbody').html('')
          //             $('.r-jw-bjgl-add-student .r-tbody').html(html);
          //           }
          //         })
          //       }
          //     });
          //   }
          // });
          $.post('/pjsys/CountStudentByClassInfoId.action', {
            classinfo_id: bj_index
          }, function (data) {
            $('.r-jw-bjgl-add-student .r-wenzi span').eq(3).text(data)
          });
        });
      } else if (data === 3) {
        layer.msg('该班级名额已满，添加失败！', {
          time: 2000
        })
      } else {
        layer.msg('添加失败,', {
          time: 2000
        })
      }
    });
  })
  //导出班级
  $('.export_btn').click(function () {
    var id = $('.r-jw-bjgl-add-student .r-wenzi span').eq(1).attr('index-id');
    window.location.href = '/pjsys/download.action?classinfo_id=' + id;
  })
  $('.r-bjgl-dc').click(function () {
    window.location.href = '/pjsys/download.action'
  })
// ----------班级管理结束----------
// ==============班主任管理开始-=-======

  $('.nav-teacher').click(function(e){
    teacherList();
    $(".r-jw-teacher").show().siblings().hide();
    e.stopPropagation()
  })
  // 显示班主任列表
  function teacherList () {
    $.post("/pjsys/queryPageHeadMaster", { pageNum: 1, pageSize: 10 }, function (data) {
      if (data.total > 0) {
        laypage.render({
          elem: 'teacher-page'
          , count: data.total
          , theme: "#65daf7"
          , first: '首页'
          , last: '尾页'
          , jump: function (obj, frist) {
            $.post("/pjsys/queryPageHeadMaster", { pageNum: obj.curr, pageSize: 10 }, function (data) {
              var html = ''
              for (var i = 0; i < data.datas.length; i++) {
                html += '<tr class="r-tbody-tr"><td>' + data.datas[i].name + "</td><td>" + data.datas[i].sex + "</td><td>" + data.datas[i].card + "</td><td>" + data.datas[i].phonenumber + "</td><td>" + data.datas[i].comment + '</td><td><span class="xg-bg xg-teacher" index_id="' + data.datas[i].id + '">修改</span><span class="del-bg" index_id="' + data.datas[i].id + '">删除</span><span class="xg-bg teacher-class" index_id="' + data.datas[i].id + '">已配置班级</span><span class="xg-bg teacher-new-class" index_id="' + data.datas[i].id + '">新增班级</span></td></tr>';
              }
              $('.r-jw-teacher .r-tbody').html(html)
            })
          }
        })
      }
    });
  }
  // 班主任删除
  $("body").on("click", ".r-jw-teacher .r-tbody-tr .del-bg",function(){
    $.post("pjsys/deleteHeadMaster",{id: $(this).attr('index_id')},function(data){
      if(data === 1){
        layer.msg('成功删除')
        teacherList() 
      }else{
        layer.msg('请重新尝试')
      }
    });
  });
  // -----------新增班主任
  
// ==============班主任管理结束-=-======
  $(".r-teacher-new").click(function(e){
    $(".fade-teacher").show();
    e.stopPropagation()
  });
  // 确定新增班主任
  $("body").on("click", ".fade-teacher .btn-teacher",function(e){
    name = $(".teacher-name").val()
    sex = $(".teacher-sex").val()
    card = $(".teacher-id").val()
    phonenumber = $(".teacher-tel").val()
    comment = $(".teacher-content").val()
    if (name == "" || sex == "" || card == "" || phonenumber == '' || comment == '') {
      layer.msg('请按照要求提示填写')
    }else{
      $.post("/pjsys/addHeadMaster",{name: name,sex: sex,card: card,phonenumber: phonenumber,comment: comment},function (data) {
          if (data === 1) {
            layer.msg("添加成功", { time: 1000 }, function () {
              teacherList();
              $(".fade-teacher").hide();
            });
          } else {
            layer.msg("添加失败,请重新尝试", { time: 2000 });
          }
        }
      );
    }
    e.stopPropagation();
  });
  // 取消新增班主任
  $("body").on("click", ".fade-teacher .btn-teacher-del",function(){
    $(".fade-teacher").hide();
  });
  // 修改班主任
  $("body").on("click", ".r-jw-teacher .r-tbody .xg-teacher",function(){
    id=$(this).attr('index_id')
    $(this).parents('tr').find('td').eq(0).text()
    $(this).parents('tr').find('td').eq(1).text()
    $(this).parents('tr').find('td').eq(2).text()
    $(this).parents('tr').find('td').eq(3).text()
    $(".fade-xg-teacher .teacher-xg-name").val($(this).parents('tr').find('td').eq(0).text());
    $(".fade-xg-teacher .teacher-xg-sex").val($(this).parents('tr').find('td').eq(1).text());
    $(".fade-xg-teacher .teacher-xg-id").val($(this).parents('tr').find('td').eq(2).text());
    $(".fade-xg-teacher .teacher-xg-tel").val($(this).parents('tr').find('td').eq(3).text());
    $(".fade-xg-teacher .teacher-xg-content").val($(this).parents('tr').find('td').eq(4).text());
    $(".teacher-xg-name").attr('index_id', id);
    $(".fade-xg-teacher").show();
  });
  // 确认修改班主任
  $("body").on("click", ".fade-xg-teacher .btn-xg-teacher",function(){
    var id = ''
    id = $(".teacher-xg-name").attr("index_id");
    name = $(".teacher-xg-name").val()
    sex = $(".teacher-xg-sex").val()
    card = $(".teacher-xg-id").val()
    phonenumber = $(".teacher-xg-tel").val()
    comment = $(".teacher-xg-content").val()
    $.post("/pjsys/updateHeadMaster", { id: id, name: name, sex: sex, card: card, phonenumber: phonenumber, comment: comment }, function (data) {
      if (data === 1) {
        layer.msg('修改成功', { time: 2000 }, function () {
          teacherList();
          $(".fade-xg-teacher").hide();
        })
      }else{
        layer.msg('修改失败,请重新尝试', { time: 2000 })
      }
    });
  });
  // 取消修改班主任
  $("body").on("click", ".fade-xg-teacher .btn-xg-teacher-del",function(){
    $(".fade-xg-teacher").hide();
  });
  // 已配置班级
  $("body").on("click", ".r-jw-teacher .r-tbody .r-tbody-tr .teacher-class",function(){
    var id = $(this).attr('index_id')
    $(".fade-old-teacher .fade-center-left p").attr("index_id",id);
    classinfo_old(id);
    $(".fade-old-teacher").show();
  });
  function classinfo_old(id) {
    var html = ''
    $.post('/pjsys/classinfoInheadmaster_idIsTrue', { id: id}, function (data) {
      if (data.length > 0) {
        for (var i = 0; i < data.length; i++) {
          html += '<tr class="r-tbody-tr"><td>' + data[i].classname + '</td><td><span class="del-bg" index_id="' + data[i].id + '">删除</span></td></tr>';
        }
      } else {
        html += '<tr class="r-tbody-tr"><td colspan="2">暂时没有班级</td></tr>';
      }
      $("body .fade-old-teacher .r-tbody").html(html);
    })
  }
  // 已配置班级删除
  $("body").on('click','.fade-old-teacher .fade-center .del-bg',function(){
    var id = $(".fade-old-teacher .fade-center-left p").attr('index_id');
    $(this).attr('index_id')
    $.post("/pjsys/classinfoClearheadmaster_id", { classinfo_id: $(this).attr('index_id')},function(data){
      if(data===1){
        layer.msg('成功删除',{time:2000},function(){
          classinfo_old(id);
        });
      }else{
        layer.msg('删除失败')
      }
    });
  });
  // 确定已配置班级
  $("body").on("click",".fade-old-teacher .btn-teacher-class", function() {
    $(".fade-old-teacher").hide();
  });
  // 新增班级
  $("body").on("click", ".r-jw-teacher .r-tbody .teacher-new-class",function(e){
    $.post('/pjsys/classinfoInheadmaster_idIsNull',function(data){
      var html = '<option value=""></option>'
      for(var i=0;i<data.length;i++){
        html += '<option value="'+data[i].id+'">'+data[i].classname+'</option>'
      }
      $("#teacher_class").html(html);
      form.render("select");
    })
    var id = $(this).attr("index_id");
    $(".fade-add-teacher-class .fade-center-left p").attr("id",id);
    $('.fade-add-teacher-class').show()
    e.stopPropagation()
  });
  $("body").on("click", ".fade-add-teacher-class .btn-add-teacher-class", function () {
    var id = $(".fade-add-teacher-class .fade-center-left p").attr('id')
    var classinfo_id = $("#teacher_class").val();
    $.post("/pjsys/classinfoUpdateheadmaster_id", { id: id, classinfo_id: classinfo_id }, function (data) {
      if (data === 1) {
        layer.msg('新加成功', { time: 1000 }, function () {
          $(".fade-add-teacher-class").hide();
        })
      } else {
        layer.msg('未成功添加,请重新尝试')
      }
    });
  });
  // 确定新增
  // 取消新增
  $("body").on("click", ".fade-add-teacher-class .btn-add-teacher-class-del", function () {
    $(".fade-add-teacher-class").hide();
  });
  //----------面试审核开始----------
  //=========按照姓名查找
  $('.search-interview-student').on('click', function () {
    $('.div-interview-auditing').hide();
    $('.div-find-interview-auditing').show();
  })
  $('.btn-return-interview-student').on('click', function () {
    $('.div-find-interview-auditing').hide();
    $('.div-interview-auditing').show();
  })
  //==========按照姓名添加
  $('.add-interview-student').on('click', function () {
    $('.fade-interview-add-student').show();
  })
  $('.return-interview-add-student').on('click', function () {
    $('.fade-interview-add-student').hide();
  })
  // 面试科目
  function interviewSubject() {
    $.post('/pjsys/findAllInterview.action',function(data){
      var html= '<option value=""></option>'
      for(var i=0;i<data.length;i++){
        html += '<option value="' + data[i].id + '">' + data[i].interviewSubject + '</option>'
      }
      $('.r-jw-mssh .r-wenzi select[name="interview-subject-sel"]').html(html)
      form.render('select');
    })
  }
  // 面试全部学生
  function interviewList () {
    $.post('/pjsys/findResult1.action', {page: 1}, function (data) {
      mssh_total = data.total;
      if (data.length == 0) {
        $('.r-jw-mssh .div-interview-auditing .r-tbady-shenhe').html('<tr class="r-tbody-tr"><td colspan="7">暂时没有数据</td></tr>')}
        else {
          laypage.render({
          elem: 'auditing-interview'
          ,count: mssh_total
          ,theme: "#65daf7",
          jump: function (obj, first) {
            $.post('/pjsys/findResult1.action', {page: obj.curr}, function (data) {
              if (data.datas == null) {
                $('.r-jw-mssh .div-interview-auditing .r-tbady-shenhe').html('<tr class="r-tbody-tr"><td colspan="7">暂无面试相关数据</td></tr>');
                return false;
              }
                else {
                  var html;
                  var result;
                  var sqtime;
                  var shtime;
                  for (var i = 0; i < data.datas.length; i++) {
                    if (data.datas[i].result == 1) {
                        result = '通过'
                      } else if (data.datas[i].result == 2) {
                        result = '不通过'
                      } else if (data.datas[i].result == 0) {
                        result = '未审核'
                      }
                    (data.datas[i].sqDt == null || data.datas[i].sqDt == "") ? (sqtime = "暂无时间") : (sqtime = data.datas[i].sqDt);
                    (data.datas[i].shDt == null || data.datas[i].shDt == "") ? (shtime = "暂无时间") : (shtime = data.datas[i].shDt);
                        html += '<tr class="r-tbody-tr">\n' +
                          '                            \n' +
                          '                            <td>' + data.datas[i].student.stuName + '</td>\n' +
                          '                            <td>' + data.datas[i].student.personalNum + '</td>\n' +
                          '                            <td>' + data.datas[i].interview_timeslot.interview.interviewSubject + '</td>\n' +
                          '                            <td>' + result + '</td>\n' +
                          '                            <td>' + sqtime + '</td>\n' +
                          '                            <td>' + shtime + '</td>\n' +
                          '                            <td>\n' +
                          '                                <span class="xg-bg" stu-id="' + data.datas[i].student.id + '" interview-id="' + data.datas[i].interview_timeslot.id + '">通过</span>\n' +
                          '                                <span class="del-bg" stu-id="' + data.datas[i].student.id + '" interview-id="' + data.datas[i].interview_timeslot.id + '">不通过</span>\n' +
                          '                            </td>\n' +
                          '                        </tr>'
                  }
                  $('.r-jw-mssh .div-interview-auditing .r-tbady-shenhe').html(html);
                }
              })
            }
          })
        }
      });
    }
  var mssh_total = 2;
  $('.nav-mssh').on('click', function (e) {
    interviewSubject()
    interviewList()
    $('.r-jw-mssh').show().siblings().hide();
    e.stopPropagation()
  })
  // 通过审核
  $('body').on('click', '.r-jw-mssh .r-table .r-tbady-shenhe .xg-bg', function () {
    var stu_id = $(this).attr('stu-id');
    var indexid = $('.r-jw-mssh .r-wenzi select[name="interview-subject-sel"]').val()
    var interview_timeslot_id = $(this).attr('interview-id');
    $.post('/pjsys/pass.action', {
      stu_id: stu_id,
      interview_timeslot_id: interview_timeslot_id
    }, function (data) {
      if (data == 1) {
        layer.msg('面试通过')
      } else {
        layer.msg('操作失败，请重新审核')
        return false;
      }
      interviewSubjectSel(indexid);
    })
  });
  // 未通过审核
  $('body').on('click', '.r-jw-mssh .r-table .r-tbady-shenhe .del-bg', function () {
    var stu_id = $(this).attr('stu-id');
    var indexid = $('.r-jw-mssh .r-wenzi select[name="interview-subject-sel"]').val()
    var interview_timeslot_id = $(this).attr('interview-id');
    $.post('/pjsys/unpass.action', {
      stu_id: stu_id,
      interview_timeslot_id: interview_timeslot_id
    }, function (data) {
      if (data == 2) {
        layer.msg('操作成功，面试未通过')
      } else {
        layer.msg('操作失败')
        return false;
      }
      interviewSubjectSel(indexid);
    })
  });
  // 检测select下拉变化
  form.on('select(interview-subject-sel)', function (data) {
    interviewSubjectSel(data.value);
  })
  // 按照科目来进行面试审核
  function interviewSubjectSel (index){
    if(index == ''){
      interviewList()
    }else{
      $.post('/pjsys/findByInterview.action', {interview_id: index,page: 1}, function (data) {
        mssh_total = data.total;
        laypage.render({
          elem: 'auditing-interview'
          ,count: mssh_total
          ,theme: "#65daf7"
          ,jump: function (obj, first) {
            $.post('/pjsys/findByInterview.action', {
              interview_id: index,
              page: obj.curr
            }, function (data) {
              if (data.datas == null || data.datas =='') {
                $('.r-jw-mssh .div-interview-auditing .r-tbady-shenhe').html('<tr class="r-tbody-tr"><td colspan="7">暂无数据</td></tr>');
              } else {
                var html;
                var result;
                var sqtime;
                var shtime;
                for (var i = 0; i < data.datas.length; i++) {
                  if (data.datas[i].result == 1) {
                    result = '通过'
                  } else if (data.datas[i].result == 2) {
                    result = '不通过'
                  } else if (data.datas[i].result == 0) {
                    result = '未审核'
                  }
                  (data.datas[i].sqDt == null || data.datas[i].sqDt == "") ? (sqtime = "暂无时间") : (sqtime = data.datas[i].sqDt);
                  (data.datas[i].shDt == null || data.datas[i].shDt == "") ? (shtime = "暂无时间") : (shtime = data.datas[i].shDt);
                    html += '<tr class="r-tbody-tr">\n' +
                      '                            \n' +
                      '                            <td>' + data.datas[i].student.stuName + '</td>\n' +
                      '                            <td>' + data.datas[i].student.personalNum + '</td>\n' +
                      '                            <td>' + data.datas[i].interview_timeslot.interview.interviewSubject + '</td>\n' +
                      '                            <td>' + result + '</td>\n' +
                      '                            <td>' + sqtime + '</td>\n' +
                      '                            <td>' + shtime + '</td>\n' +
                      '                            <td>\n' +
                      '                                <span class="xg-bg" stu-id="' + data.datas[i].student.id + '" interview-id="' + data.datas[i].interview_timeslot.id + '">通过</span>\n' +
                      '                                <span class="del-bg" stu-id="' + data.datas[i].student.id + '" interview-id="' + data.datas[i].interview_timeslot.id + '">不通过</span>\n' +
                      '                            </td>\n' +
                      '                        </tr>'
                }
                $('.r-jw-mssh .div-interview-auditing .r-tbady-shenhe').html(html);
              }
            })
          }
        })
      
    })
    }
  }
  // 面试审核 清空
  $('.r-jw-mssh .btn-clear').click(function(){
    $.post('/pjsys/interview_infoInclear_flag',function(data){
      if(data === 1){
        layer.msg('清空学生成功',{time: 2000})
      }else{
        layer.msg('失败,请重新尝试')
      }
    })
  })
  // 面试审核 搜索
  $('.btn-find-interview-student').click(function () {
    var name = $('#find-interview-student').val();
    if (name == '') {
      layer.msg('请输入要搜索的名字');
      return false;
    }
    $.post('/pjsys/show1.action', {
      stuName: name
    }, function (data) {
      if (data == null || data == [] || data == 'null') {
        $('.div-find-interview-auditing .r-table .r-tbady-shenhe-search').html('<tr class="r-tbody-tr"><td colspan="7">查询不到此学生的未审核信息</td></tr>');
        return false;
      } else {
        var html;
        var result;
        var sqtime;
        var shtime;
        for (var i = 0; i < data.length; i++) {
          if (data[i].result == 1) {
            result = '通过'
          } else if (data[i].result == 2) {
            result = '不通过'
          } else if (data[i].result == 0) {
            result = '未审核'
          }
          (data[i].sqDt == null || data[i].sqDt == "") ? (sqtime = "暂无时间") : (sqtime = data[i].sqDt);
          (data[i].shDt == null || data[i].shDt == "") ? (shtime = "暂无时间") : (shtime = data[i].shDt);
        for (var i = 0; i < data.length; i++) {
          html += '<tr class="r-tbody-tr">\n' +
            '                            \n' +
            '                            <td>' + data[i].student.stuName + '</td>\n' +
            '                            <td>' + data[i].student.personalNum + '</td>\n' +
            '                            <td>' + data[i].interview_timeslot.interview.interviewSubject + '</td>\n' +
            '                            <td>' + result + '</td>\n' +
            '                            <td>' + sqtime + '</td>\n' +
            '                            <td>' + shtime + '</td>\n' +
            '                            <td>\n' +
            '                                <span class="xg-bg" stu-id="' + data[i].student.id + '"  tinterview_timeslot_id-index="' + data[i].interview_timeslot.id + '" >通过</span>\n' +
            '                                <span class="del-bg" stu-id="' + data[i].student.id + '"  tinterview_timeslot_id-index="' + data[i].interview_timeslot.id + '" >不通过</span>\n' +
            '                            </td>\n' +
            '                        </tr>'
        }
        $('.div-find-interview-auditing .r-table .r-tbady-shenhe-search').html(html);
      }
      }
    })
  });
  $('.div-find-interview-auditing').on('click', '.r-table .r-tbady-shenhe-search .xg-bg', function () {
    var stu_id = $(this).attr('stu-id');
    var interview_timeslot_id = $(this).attr('tinterview_timeslot_id-index');
    $.post('/pjsys/pass.action', {
      stu_id: stu_id,
      interview_timeslot_id: interview_timeslot_id
    }, function (data) {
      if (data == 1) {
        layer.msg('面试通过')
      } else {
        layer.msg('操作失败，请重新审核')
        return false;
      }
      $('.btn-find-interview-student').click();
      //重新刷新数据
    })
  });
  $('.div-find-interview-auditing').on('click', '.r-table .r-tbady-shenhe-search .del-bg', function () {
    var stu_id = $(this).attr('stu-id');
    var interview_timeslot_id = $(this).attr('tinterview_timeslot_id-index');
    $.post('/pjsys/unpass.action', {
      stu_id: stu_id,
      interview_timeslot_id: interview_timeslot_id
    }, function (data) {
      if (data == 2) {
        layer.msg('操作成功，面试未通过')
      } else {
        layer.msg('操作失败')
        return false;
      }
      $('.btn-find-interview-student').click();
    })
  });
  //----------面试审核结束----------


  //----------教室管理开始----------
  // -----教室管理
  $('.nav-jsgl').on('click', function (e) {
    $.post('/pjsys/findAllClassroom.action', function (data) {
      var html = '';
      var htmls = '<option value=""></option>';
      for (var i = 0; i < data.length; i++) {

        html += '<li style="width: 147.25px;float: left;text-align: center;" data-id="' + data[i].id + '" >' + data[i].name + '</li>'
        htmls += '<option value="' + data[i].id + '">' + data[i].name + '</option>'
      }
      $('.r-jw-jsgl .jspb-old .r-jsgl ul').html(html);
      $('.fade-jsgl-time .fade-jsgl-center-time .layui-form-item .layui-input-block select[name="xz-timer-class"]').html(htmls);
      form.render("select")
    })
    $('.r-jw-jsgl').show().siblings().hide();
    e.stopPropagation()
  })
  // --------------新增教室
  $('.r-jsgl-xz').on('click', function () {
    $('.fade-jsxz').show();
  })
  // 新增教室
  $('.fade-jzxz-btn').on('click', function () {
    var text_name = $('.fade-jsxz .fade-center-jsxz .fade-center-left input.input').val();
    if(text_name ==''){
      layer.msg('请输入教室名',{time:2500})
    }else{
      $.post('/pjsys/addClassroom.action', {
        name: text_name
      }, function (data) {
        if (data === 1) {
          layer.msg('新增成功', {
            time: 1000
          }, function () {
            $('.fade-jsxz').hide();
            $('.nav-jsgl').click();
          });
        } else {
          layer.msg('新增失败');
        }
      })
    }
  })
  // 取消新增教室
  $('.fade-jzxz-btn-del').on('click', function () {
    $('.fade-jsxz').hide();
  })
  // ------新增时间段
  $('.r-jsgl-timer').on('click', function () {
    $('.fade-jsgl-time').show();
  })
  //------取消新增
  $('.fade-btn-time-del').on('click', function () {
    $('.fade-jsgl-time').hide();
  })
  //------确定新增
  $('.fade-btn-time').on('click', function () {
    var classroom_id = $('.fade-jsgl-time .fade-jsgl-center-time .layui-form-item .layui-input-block select[name="xz-timer-class"]').val();
    var day = $('.fade-jsgl-time .fade-jsgl-center-time .layui-form-item .layui-input-block select[name="xz-timer-week"]').val();
    var start = $('.fade-jsgl-time .fade-jsgl-center-time .layui-form-item .layui-input-block input[name="xz-timer-start-time"]').val();
    var jieshu = $('.fade-jsgl-time .fade-jsgl-center-time .layui-form-item .layui-input-block input[name="xz-timer-end-time"]').val();

    if (classroom_id == '') {
      layer.msg('请选择教室');
      return false;
    }
    if (day == 0) {
      layer.msg('请选择周期');
      return false;
    }
    if (start == '') {
      layer.msg('请选择开始时间');
      return false;
    }
    if (jieshu == '') {
      layer.msg('请选择结束时间');
      return false;
    }

    $.post('/pjsys/addClassroominfo.action', {
      classroom_id: classroom_id,
      day: day,
      start: start,
      jieshu: jieshu
    }, function (data) {

      if (data === 1) {
        layer.msg('新增时间段成功', {
          time: 1000
        }, function () {
          $('.fade-jsgl-time').hide();
        });
      } else {
        layer.msg('新增时间段失败');
      }
    });
  })

  // 班级排班
  $('body').on('click', '.r-jsgl ul li', function () {
    $('.alert_jspb .title_pb').text('当前教室--' + $(this).text())
    $('.alert_jspb .title_pb').attr('js-id', $(this).attr('data-id'));
    $.post('/pjsys/findAll1.action', function (data) {
      var hmtl = '<option value=""></option>';
      for (var i = 0; i < data.length; i++) {
        hmtl += '<option value="' + data[i].id + '">' + data[i].classname + '</option>';
      }
      $('.alert_jspb select[name="js_bj_select"]').html(hmtl)
      form.render("select")
    })
    $.post('pjsys/findRoom.action', {
      classroom_id: $('.alert_jspb .title_pb').attr('js-id')
    }, function (data) {
      var hmtl;
      if (data.length == 0) {
        hmtl += '<tr><td colspan="2" style="text-align: center;" >暂无数据</td></tr>';
      } else {
        for (var i = 0; i < data.length; i++) {
          var day = '';
          if (data[i].classroominfo.day === 1) {
            day = '星期一'
          } else if (data[i].classroominfo.day === 2) {
            day = '星期二'
          } else if (data[i].classroominfo.day === 3) {
            day = '星期三'
          } else if (data[i].classroominfo.day === 4) {
            day = '星期四'
          } else if (data[i].classroominfo.day === 5) {
            day = '星期五'
          } else if (data[i].classroominfo.day === 6) {
            day = '星期六'
          } else if (data[i].classroominfo.day === 7) {
            day = '星期日'
          }
          hmtl += '<tr><td>' + data[i].classname + '</td><td>' + day + '：' + data[i].classroominfo.start + '--' + data[i].classroominfo.jieshu + '</td> </tr>';
        }
      }
      $('.alert_jspb .layui-table tbody').html(hmtl)
    })
    $.post('/pjsys/percent.action', {
      classroom_id: $('.alert_jspb .title_pb').attr('js-id')
    }, function (data) {
      $('.alert_jspb .jszyl').text(data)
    })
    $.post('/pjsys/findAllClassroominfo.action', {
      classroom_id: $('.alert_jspb .title_pb').attr('js-id')
    }, function (data) {
      if (data == null) {
        $('.alert_jspb select[name="bj_time_select"]').html('<option value="">暂无时间段</option>')
      } else {
        var hmtl = '<option value=""></option>';
        for (var i = 0; i < data.length; i++) {
          var day = '';
          if (data[i].day === 1) {
            day = '星期一'
          } else if (data[i].day === 2) {
            day = '星期二'
          } else if (data[i].day === 3) {
            day = '星期三'
          } else if (data[i].day === 4) {
            day = '星期四'
          } else if (data[i].day === 5) {
            day = '星期五'
          } else if (data[i].day === 6) {
            day = '星期六'
          } else if (data[i].day === 7) {
            day = '星期日'
          }
          hmtl += '<option value="' + data[i].id + '">' + day + '：' + data[i].start + '--' + data[i].jieshu + '</option>';
        }
        $('.alert_jspb select[name="bj_time_select"]').html(hmtl)
        form.render("select")
      }
    })
    if (user_role == 1 || user_role == 4) {
      $('.alert_jspb').fadeIn()
    }
  })
  $('.alert_jspb .alert_jspb_Close').click(function () {
    $('.alert_jspb').fadeOut()
  })
  $('.alert_jspb .alert_jspb_add').click(function () {
    form.render("select")
    var id = $('.alert_jspb select[name="js_bj_select"]').val();
    var classroominfo_id = $('.alert_jspb select[name="bj_time_select"]').val();
    if (id == '') {
      layer.msg('请选择班级');
      return false;
    }
    if (classroominfo_id == '') {
      layer.msg('请选择班级时间段');
      return false;
    }
    $.post('/pjsys/selectClassroom.action', {
      id: id,
      'classroominfo.id': classroominfo_id
    }, function (data) {
      if (data === 1) {
        layer.msg('排班成功', {
          time: 1000
        }, function () {
          $('.alert_jspb').fadeOut()
        });
      } else {
        layer.msg('排班失败');
      }
    })
  })
  $('.r-jw-jsgl ul li').hover(function () {
    layer.tips('点击为教室排班', $('.r-jw-jsgl ul li'));
  })
  // 教室占有率
  $('.r-jsgl-zyl').on('click', function () {
    $('.fade-jszyl').show();
    $.post('/pjsys/findAllClassroom.action', function (data) {
      //  $('.fade-jszyl #ClassRoom')
      var htmls = '<option value="0"></option>';
      for (var i = 0; i < data.length; i++) {
        htmls += '<option value="' + data[i].id + '">' + data[i].name + '</option>';
      }

      $('.fade-jszyl select[name="classRoom"]').html(htmls);
      form.render('select');
    })

    function classRoom(classinfo_id) {
      var classroom_id = $('.fade-jszyl select[name="classRoom"]').val();
      $.post('/pjsys/percent.action', {
        classroom_id: classroom_id
      }, function (data) {
        var html = '';
        html += data;

        $('.fade-jszyl .zyl-input').val(html)
        form.render('select')
      })
    }
    form.on('select(classRoom)', function (data) {
      classRoom(data.value)
    })

    // 教室占有率确定
    $('.fade-jszyl-btn-del').on('click', function () {
      $('.fade-jszyl').hide();
      $('.fade-jszyl .zyl-input').val('')
    })
  })
  //----------教室管理结束----------

  //----------学生管理开始----------
  // -----查看学生信息
  $('.nav-bzr-ckxsxx').on('click', function (e) {
    //     /*分页功能*/
    // 查看学生信息
    var total; //总页数
    var current;
    var page = 1;
    var html;
    $.post('/pjsys/findAllstudent.action', {
      page: page
    }, function (data) {
      total = data.pages;
      current = data.current;
      for (var i = 0; i < data.datas.length; i++) {
        var sex;
        var attr_index = ' stuName="' + data.datas[i].stuName + '" indexid="' + data.datas[i].id + '" userid="' + data.datas[i].user.id + '" sex="' + data.datas[i].sex + '" personalNum="' + data.datas[i].personalNum + '" schoolname="' + data.datas[i].schoolname + '" address="' + data.datas[i].address + '" parent="' + data.datas[i].parent + '" mobilephone="' + data.datas[i].mobilephone + '" workaddress="' + data.datas[i].workaddress + '" index-id="' + data.datas[i].id + '" emergencyContactName="' + data.datas[i].emergencyContactName + '" emergencyContactPhone="' + data.datas[i].emergencyContactPhone + '" postcode="' + data.datas[i].postcode + '"';
        if (data.datas[i].sex === 1) {
          sex = '男'
        } else if (data.datas[i].sex === 2) {
          sex = '女'
        } 
        if (user_role == 1 || user_role == 4) {
          html += '<tr class= "r-tbody-tr" > <td>' + data.datas[i].stuName + '</td><td>' + sex + '</td> <td>' + data.datas[i].personalNum + '</td><td>' + data.datas[i].schoolname + '</td> <td>' + data.datas[i].address + '</td><td>' + data.datas[i].parent + '</td><td>' + data.datas[i].mobilephone + '</td> <td>' + data.datas[i].workaddress + '</td><td><span class="xq-bg r-details-student" ' + attr_index + ' >详情</span> <span class="xg-bg r-check-xg-student" ' + attr_index + ' >修改</span> <span class="xg-bg r-student-xsubject" ' + attr_index + ' indx-id="' + data.datas[i].id + '">选课</span></td> </tr>'
        } else {
          html += '<tr class= "r-tbody-tr"> <td>' + data.datas[i].stuName + '</td><td>' + sex + '</td> <td>' + data.datas[i].personalNum + '</td><td>' + data.datas[i].schoolname + '</td> <td>' + data.datas[i].address + '</td><td>' + data.datas[i].parent + '</td><td>' + data.datas[i].mobilephone + '</td> <td>' + data.datas[i].workaddress + '</td><td><span class="xq-bgs" ' + attr_index + ' >详情</span> <span class="xg-bgs" ' + attr_index + ' >修改</span> <span class="xg-bgs" ' + attr_index + ' indx-id="' + data.datas[i].id + '">选课</span></td> </tr>'
        }
      } 
      $('.student-msg .r-check-student').html(html)
      $('.M-box').pagination({ //total不能少
        pageCount: total,
        current: current,
        coping: true,
        homePage: '首页',
        endPage: '末页',
        callback: function (api, jq) {
          var htmls;
          $.post('/pjsys/findAllstudent.action', {
            page: api.getCurrent()
          }, function (datas) {
            $('.student-msg .r-check-student').html('')

            for (var i = 0; i < datas.datas.length; i++) {
              var sex;
              var attr_index = ' stuName="' + datas.datas[i].stuName + '" indexid="' + datas.datas[i].id + '" userid="' + datas.datas[i].user.id + '" sex="' + datas.datas[i].sex + '" personalNum="' + datas.datas[i].personalNum + '" schoolname="' + datas.datas[i].schoolname + '" address="' + datas.datas[i].address + '" parent="' + datas.datas[i].parent + '" mobilephone="' + datas.datas[i].mobilephone + '" workaddress="' + datas.datas[i].workaddress + '" index-id="' + datas.datas[i].id + '" emergencyContactName="' + datas.datas[i].emergencyContactName + '" emergencyContactPhone="' + datas.datas[i].emergencyContactPhone + '" postcode="' + datas.datas[i].postcode + '"';
              if (datas.datas[i].sex === 1) {
                sex = '男'
              } else if (datas.datas[i].sex === 2) {
                sex = '女'
              }
              // console.log(datas);
              if (user_role == 1 || user_role == 4) {
                htmls += '<tr class= "r-tbody-tr" > <td>' + datas.datas[i].stuName + '</td><td>' + sex + '</td> <td>' + datas.datas[i].personalNum + '</td><td>' + datas.datas[i].schoolname + '</td> <td>' + datas.datas[i].address + '</td><td>' + datas.datas[i].parent + '</td><td>' + datas.datas[i].mobilephone + '</td> <td>' + datas.datas[i].workaddress + '</td><td><span class="xq-bg r-details-student" ' + attr_index + ' >详情</span> <span class="xg-bg r-check-xg-student" ' + attr_index + ' >修改</span> <span class="xg-bg r-student-xsubject" ' + attr_index + ' indx-id="' + datas.datas[i].id + '">选课</span></td> </tr>'
              } else {
                htmls += '<tr class= "r-tbody-tr"> <td>' + datas.datas[i].stuName + '</td><td>' + sex + '</td> <td>' + datas.datas[i].personalNum + '</td><td>' + datas.datas[i].schoolname + '</td> <td>' + datas.datas[i].address + '</td><td>' + datas.datas[i].parent + '</td><td>' + datas.datas[i].mobilephone + '</td> <td>' + datas.datas[i].workaddress + '</td><td><span class="xq-bgs" ' + attr_index + ' >详情</span> <span class="xg-bgs" ' + attr_index + ' >修改</span> <span class="xg-bgs" ' + attr_index + ' indx-id="' + datas.datas[i].id + '">选课</span></td> </tr>'
              }
              // htmls+='<tr class= "r-tbody-tr" style="background: rgb(247,248,250);"> <td>'+datas.datas[i].stuName+'</td><td>'+sex+'</td> <td>'+datas.datas[i].personalNum+'</td><td>'+datas.datas[i].schoolname+'</td> <td>'+datas.datas[i].address+'</td> <td>'+datas.datas[i].parent+'</td><td>'+datas.datas[i].mobilephone+'</td> <td>'+datas.datas[i].workaddress+'</td><td><span class="xq-bg r-details-student" '+attr_index+' >详情</span> <span class="xg-bg r-check-xg-student" '+attr_index+' >修改</span> <span class="xg-bg r-student-xsubject" '+attr_index+' indx-id="'+datas.datas[i].id+'">选课</span> </td> </tr>'
            }
            $('.student-msg .r-check-student').html(htmls)
            return false;
          })
        }
      });
    });
    $('.r-bzr-ckxsxx').show().siblings().hide();
    $('.student-msg').show();
    e.stopPropagation()
  })
  $('.r-bzr-ckxsxx .student-msg .r-wenzi .daoxu').click(function () {
    // $('.r-bzr-ckxsxx .student-msg .r-wenzi .btn').click(function(){
    var total; //总页数
    var current;
    var page = 1;
    var html;
    $.post('/pjsys/finddesc.action', {
      page: page
    }, function (data) {
      total = data.pages;
      current = data.current;
      for (var i = 0; i < data.datas.length; i++) {
        var sex;
        var attr_index = ' stuName="' + data.datas[i].stuName + '" indexid="' + data.datas[i].id + '" userid="' + data.datas[i].user.id + '" sex="' + data.datas[i].sex + '" personalNum="' + data.datas[i].personalNum + '" schoolname="' + data.datas[i].schoolname + '" address="' + data.datas[i].address + '" parent="' + data.datas[i].parent + '" mobilephone="' + data.datas[i].mobilephone + '" workaddress="' + data.datas[i].workaddress + '" index-id="' + data.datas[i].id + '" emergencyContactName="' + data.datas[i].emergencyContactName + '" emergencyContactPhone="' + data.datas[i].emergencyContactPhone + '" postcode="' + data.datas[i].postcode + '"';
        if (data.datas[i].sex === 1) {
          sex = '男'
        } else if (data.datas[i].sex === 2) {
          sex = '女'
        }
        html += '<tr class= "r-tbody-tr"> <td>' + data.datas[i].stuName + '</td><td>' + sex + '</td> <td>' + data.datas[i].personalNum + '</td><td>' + data.datas[i].schoolname + '</td> <td>' + data.datas[i].address + '</td><td>' + data.datas[i].parent + '</td><td>' + data.datas[i].mobilephone + '</td> <td>' + data.datas[i].workaddress + '</td><td><span class="xq-bg r-details-student" ' + attr_index + ' >详情</span> <span class="xg-bg r-check-xg-student" ' + attr_index + ' >修改</span> <span class="xg-bg r-student-xsubject" ' + attr_index + ' indx-id="' + data.datas[i].id + '">选课</span></td> </tr>'
      }
      $('.student-msg .r-check-student').html(html)
      $('.M-box').pagination({ //total不能少
        pageCount: total,
        current: current,
        coping: true,
        homePage: '首页',
        endPage: '末页',
        callback: function (api, jq) {
          var htmls;
          $.post('/pjsys/finddesc.action', {
            page: api.getCurrent()
          }, function (datas) {
            $('.student-msg .r-check-student').html('')

            for (var i = 0; i < datas.datas.length; i++) {
              var sex;
              var attr_index = ' stuName="' + datas.datas[i].stuName + '" indexid="' + datas.datas[i].id + '" userid="' + datas.datas[i].user.id + '" sex="' + datas.datas[i].sex + '" personalNum="' + datas.datas[i].personalNum + '" schoolname="' + datas.datas[i].schoolname + '" address="' + datas.datas[i].address + '" parent="' + datas.datas[i].parent + '" mobilephone="' + datas.datas[i].mobilephone + '" workaddress="' + datas.datas[i].workaddress + '" index-id="' + datas.datas[i].id + '" emergencyContactName="' + datas.datas[i].emergencyContactName + '" emergencyContactPhone="' + datas.datas[i].emergencyContactPhone + '" postcode="' + datas.datas[i].postcode + '"';
              if (datas.datas[i].sex === 1) {
                sex = '男'
              } else if (datas.datas[i].sex === 2) {
                sex = '女'
              }
              htmls += '<tr class= "r-tbody-tr"> <td>' + datas.datas[i].stuName + '</td><td>' + sex + '</td> <td>' + datas.datas[i].personalNum + '</td><td>' + datas.datas[i].schoolname + '</td> <td>' + datas.datas[i].address + '</td> <td>' + datas.datas[i].parent + '</td><td>' + datas.datas[i].mobilephone + '</td> <td>' + datas.datas[i].workaddress + '</td><td><span class="xq-bg r-details-student" ' + attr_index + ' >详情</span> <span class="xg-bg r-check-xg-student" ' + attr_index + ' >修改</span> <span class="xg-bg r-student-xsubject" ' + attr_index + ' indx-id="' + datas.datas[i].id + '">选课</span> </td> </tr>'
            }
            $('.student-msg .r-check-student').html(htmls)
            return false;
          })
        }
      });
    });
  })
  // 导出学生
  $('body').on('click', '.r-bzr-ckxsxx .export-btn', function () {
    $('.export-student').show();
  })
  $('.export-fade-btn-del').click(function () {
    $('.export-student').hide();
  })
  $('.export-fade-btn').click(function () {
    $('.export-student').hide();
  })
  // 搜索学生
  $('.find-stuname-div .btn-search').click(function () {
    var name = $('#find-name').val();
    if (name == '') {
      layer.msg('学生名字不能为空');
      return false;
    }
    loading("数据加载中，请稍后！", false);
    $.post('pjsys/show.action', {
      stuName: name
    }, function (data) {
        loading("加载成功", true);
      if (data.length == 0) {
        $('.find-stuname-div .r-find-student').html('');
        $('.find-stuname-div .r-find-student').html('<tr class="r-tbody-tr"><td colspan="12">没有查询到该学生信息</td></tr>')
      } else {
        var html;
        for (var i = 0; i < data.length; i++) {
          var sex;
          var attr_index = ' stuName="' + data[i].stuName + '"indexid="' + data[i].id + '" userid="' + data[i].user.id + '" sex="' + data[i].sex + '" personalNum="' + data[i].personalNum + '" schoolname="' + data[i].schoolname + '" address="' + data[i].address + '" parent="' + data[i].parent + '" mobilephone="' + data[i].mobilephone + '" workaddress="' + data[i].workaddress + '" index-id="' + data[i].id + '" emergencyContactName="' + data[i].emergencyContactName + '" emergencyContactPhone="' + data[i].emergencyContactPhone + '" postcode="' + data[i].postcode + '"';
          if (data[i].sex === 1) {
            sex = '男'
          } else if (data[i].sex === 2) {
            sex = '女'
          }
          html += '<tr class= "r-tbody-tr"> <td>' + data[i].stuName + '</td><td>' + sex + '</td> <td>' + data[i].personalNum + '</td><td>' + data[i].schoolname + '</td> <td>' + data[i].address + '</td> <td>' + data[i].parent + '</td><td>' + data[i].mobilephone + '</td> <td>' + data[i].workaddress + '</td><td width="140px"><span class="xq-bg r-details-student" ' + attr_index + '  indx-id="' + data[i].id + '" >详情</span><span class="xg-bg r-check-xg-student" ' + attr_index + '  indx-id="' + data[i].id + '" >修改</span><span class="xg-bg r-student-xsubject" ' + attr_index + '  indx-id="' + data[i].id + '" >选课</span></td> </tr>'
        }
        $('.find-stuname-div .r-find-student').html(html)
      }
    }, 'json')
  })
  // 点击按姓名查找,查找栏出现
  $('.find-studentname').on('click', function () {
    $('.find-stuname-div').show();
    $('.student-msg').hide();
  })
  // 查找栏返回,搜索栏隐藏,原始信息出现
  $('.btn-return').on('click', function () {
    $('.find-stuname-div').hide();
    $('.student-msg').show();
  })
  // ===========修改学生信息
  //------修改学生
  $('body').on('click', '.r-check-xg-student', function () {
    $('.xg-fade-student input[name="xg-student-stuname"]').val($(this).attr('stuName'));
    $('.xg-fade-student input[name="xg-student-stuname"]').attr('indexid', $(this).attr('indexid'));
    $('.xg-fade-student input[name="xg-student-stuname"]').attr('userid', $(this).attr('userid'));
    $('.xg-fade-student input[name="xg-student-school"]').val($(this).attr('schoolname'));
    $('.xg-fade-student input[name="xg-student-home"]').val($(this).attr('address'));
    $('.xg-fade-student input[name="xg-sfzh"]').val($(this).attr('personalnum'));
    $('.xg-fade-student input[name="xg-student-yb"]').val($(this).attr('postcode'));
    $('.xg-fade-student input[name="xg-student-parents-name"]').val($(this).attr('parent'));
    $('.xg-fade-student input[name="xg-student-parents-tel"]').val($(this).attr('mobilephone'));
    $('.xg-fade-student input[name="xg-student-unit"]').val($(this).attr('workaddress'));
    $('.xg-fade-student input[name="xg-student-standby-name"]').val($(this).attr('emergencycontactname'));
    $('.xg-fade-student input[name="xg-student-standby-tel"]').val($(this).attr('emergencycontactphone'));
    if ($(this).attr('sex') == 1) {
      $('.xg-fade-student .layui-form>div .layui-input-block .layui-form-radio').eq(0).click()
      $('.xg-fade-student .layui-form>div .layui-input-block input[type="radio"]').eq(0).removeAttr('disabled')
      // $('.xg-fade-student .layui-form>div .layui-input-block input[type="radio"]').eq(1).attr('disabled','disabled')
      $('.xg-fade-student .layui-form>div .layui-input-block input[type="radio"]').eq(1).removeAttr('checked')
      $('.xg-fade-student .layui-form>div .layui-input-block input[type="radio"]').eq(0).attr('checked', true)

    } else {
      $('.xg-fade-student .layui-form>div .layui-input-block input[type="radio"]').eq(1).removeAttr('disabled')
      // $('.xg-fade-student .layui-form>div .layui-input-block input[type="radio"]').eq(0).attr('disabled','disabled')
      $('.xg-fade-student .layui-form>div .layui-input-block .layui-form-radio').eq(1).click()
      $('.xg-fade-student .layui-form>div .layui-input-block input[type="radio"]').eq(0).removeAttr('checked')
      $('.xg-fade-student .layui-form>div .layui-input-block input[type="radio"]').eq(1).attr('checked', true)
    }
    $('.xg-fade-student').show();
  })
  // --------取消修改
  $('.xg-student-btn-del').on('click', function () {
    $('.xg-fade-student').hide();
  })
  //  ----------确定修改
  $('.xg-student-btn').on('click', function () {
    var stuName = $('.xg-fade-student input[name="xg-student-stuname"]').val();
    var sex_text = $('.xg-fade-student input[name="xg-student-sex"]:checked').val();
    var sex;
    if (sex_text == '男') {
      sex = 1
    } else {
      sex = 2
    }
    var user_id = $('.xg-fade-student input[name="xg-student-stuname"]').attr('userid');
    var indexid = $('.xg-fade-student input[name="xg-student-stuname"]').attr('indexid');
    var schoolname = $('.xg-fade-student input[name="xg-student-school"]').val();
    var address = $('.xg-fade-student input[name="xg-student-home"]').val();
    var personalNum = $('.xg-fade-student input[name="xg-sfzh"]').val();
    var postcode = $('.xg-fade-student input[name="xg-student-yb"]').val();
    var parent = $('.xg-fade-student input[name="xg-student-parents-name"]').val();
    var mobilephone = $('.xg-fade-student input[name="xg-student-parents-tel"]').val();
    var EmergencyContactName = $('.xg-fade-student input[name="xg-student-standby-name"]').val();
    var EmergencyContactPhone = $('.xg-fade-student input[name="xg-student-standby-tel"]').val();
    var workaddress = $('.xg-fade-student input[name="xg-student-unit"]').val();

    var reg = /(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/;
    if (reg.test(personalNum) === false) {
      layer.msg("身份证输入不合法");
      return false;
    }
    $.post('pjsys/update.action', {
      user_id: user_id,
      id: indexid,
      sex: sex,
      stuName: stuName,
      schoolname: schoolname,
      address: address,
      personalNum: personalNum,
      postcode: postcode,
      parent: parent,
      mobilephone: mobilephone,
      emergencyContactName: EmergencyContactName,
      emergencyContactPhone: EmergencyContactPhone,
      workaddress: workaddress
    }, function (data) {
      if (data === 1) {
        layer.msg('修改成功', {
          time: 1000
        }, function () {

          if ($('.find-stuname-div').css('display') == 'block') {
            var name = $('#find-name').val();
            $.post('pjsys/show.action', {
              stuName: name
            }, function (data) {

              if (data.length == 0) {
                $('.find-stuname-div .r-find-student').html('');
                $('.find-stuname-div .r-find-student').html('<tr class="r-tbody-tr"><td colspan="12">没有查询到该学生信息</td></tr>')
              } else {

                var html;
                for (var i = 0; i < data.length; i++) {
                  var sex;
                  var attr_index = ' stuName="' + data[i].stuName + '"indexid="' + data[i].id + '" userid="' + data[i].user.id + '" sex="' + data[i].sex + '" personalNum="' + data[i].personalNum + '" schoolname="' + data[i].schoolname + '" address="' + data[i].address + '" parent="' + data[i].parent + '" mobilephone="' + data[i].mobilephone + '" workaddress="' + data[i].workaddress + '" index-id="' + data[i].id + '" emergencyContactName="' + data[i].emergencyContactName + '" emergencyContactPhone="' + data[i].emergencyContactPhone + '" postcode="' + data[i].postcode + '"';
                  if (data[i].sex === 1) {
                    sex = '男'
                  } else if (data[i].sex === 2) {
                    sex = '女'
                  }
                  html += '<tr class= "r-tbody-tr"> <td>' + data[i].stuName + '</td><td>' + sex + '</td> <td>' + data[i].personalNum + '</td><td>' + data[i].schoolname + '</td> <td>' + data[i].address + '</td> <td>' + data[i].parent + '</td><td>' + data[i].mobilephone + '</td> <td>' + data[i].workaddress + '</td><td width="140px"><span class="xq-bg r-details-student" ' + attr_index + '  indx-id="' + data[i].id + '" >详情</span><span class="xg-bg r-check-xg-student" ' + attr_index + '  indx-id="' + data[i].id + '" >修改</span><span class="xg-bg r-student-xsubject" ' + attr_index + '  indx-id="' + data[i].id + '" >选课</span></td> </tr>'
                }
                $('.find-stuname-div .r-find-student').html(html)
              }
            }, 'json')
          } else {
            $('.nav-bzr-ckxsxx').click()
          }
          $('.xg-fade-student').hide();
        });
      } else {
        layer.msg('修改失败');
      }
    });
  });
  //查看学生详细信息
  $('body').on('click', ' .r-details-student', function () {
    $('.details-fade-student input[name="student-details-stuname"]').val($(this).attr('stuName'));
    $('.details-fade-student input[name="student-details-school"]').val($(this).attr('schoolname'));
    $('.details-fade-student input[name="student-details-home"]').val($(this).attr('address'));
    $('.details-fade-student input[name="student-details-sfz"]').val($(this).attr('personalnum'));
    $('.details-fade-student input[name="student-details-yb"]').val($(this).attr('postcode'));
    $('.details-fade-student input[name="student-details-parents-name"]').val($(this).attr('parent'));
    $('.details-fade-student input[name="student-details-parents-tel"]').val($(this).attr('mobilephone'));
    $('.details-fade-student input[name="student-details-unit"]').val($(this).attr('workaddress'));
    $('.details-fade-student input[name="student-details-standby-name"]').val($(this).attr('emergencycontactname'));
    $('.details-fade-student input[name="student-details-standby-tel"]').val($(this).attr('emergencycontactphone'));
    if ($(this).attr('sex') == 1) {
      $('.details-fade-student .layui-form>div .layui-input-block .layui-form-radio').eq(0).click()
      $('.details-fade-student .layui-form>div .layui-input-block input[type="radio"]').eq(0).removeAttr('disabled')
      $('.details-fade-student .layui-form>div .layui-input-block input[type="radio"]').eq(1).attr('disabled', 'disabled')
    } else {
      $('.details-fade-student .layui-form>div .layui-input-block input[type="radio"]').eq(1).removeAttr('disabled')
      $('.details-fade-student .layui-form>div .layui-input-block input[type="radio"]').eq(0).attr('disabled', 'disabled')
      $('.details-fade-student .layui-form>div .layui-input-block .layui-form-radio').eq(1).click()
    }
    $('.details-fade-student').show();
  });
  $('.btn-details-student').on('click', function () {
    $('.details-fade-student').hide();
  })
  //==========学生管理之选课按钮
  var stu_id_index;
  $('body').on('click', '.r-bzr-ckxsxx .r-student-xsubject', function () {
    stu_id_index = $(this).attr('indexid');
    var stuname = $(this).attr('stuname');
    stu_xuanke()
    $.post('pjsys/findInfoById.action', {
      stu_id: $(this).attr('indexid')
    }, function (data) {
      $('.xk-xx .r-wenzi span').eq(1).html(stuname)
      var html;
      for (var i = 0; i < data.length; i++) {
        var ms_text;
        if (data[i].classinfo.subject.interview == 1) {
          ms_text = '需要面试'
          // $('input[name="interview"]').eq(0).click()
          // $('input[name="interview"]').eq(1).attr('disabled',false)
        } else {
          ms_text = '不需要面试'
          // $('input[name="interview"]').eq(1).click()
          // $('input[name="interview"]').eq(0).attr('disabled',false)
        }
        var flag_text;
        if (data[i].flag === 1) {
          flag_text = '已报名未缴费';
        } else if (data[i].flag === 2) {
          flag_text = '申请缴费';
        } else if (data[i].flag === 3) {
          flag_text = '已缴费';
        } else if (data[i].flag === 4) {
          flag_text = '退班';
        } else if (data[i].flag === 5) {
          flag_text = '申请退费';
        } else if (data[i].flag === 6) {
          flag_text = '已退费';
        } else if (data[i].flag === 7) {
          flag_text = '已报名未续费';
        } else if (data[i].flag === 8) {
          flag_text = '申请续费';
        } else if (data[i].flag === 9) {
          flag_text = '已续费';
        } else if (data[i].flag === 10) {
          flag_text = '拒绝缴费';
        } else if (data[i].flag === 11) {
          flag_text = '拒绝续费';
        } else if (data[i].flag === 12) {
          flag_text = '拒绝退费';
        }
        html += '<tr class="r-tbody-tr"><td>' + data[i].classinfo.subject.subject + '</td><td>' + data[i].classinfo.classname + '</td><td>' + ms_text + '</td><td>' + flag_text + '</td><td><span class="del-bg xk-del" indexid="' + data[i].id + '" >删除</span></td></tr>';
      }
      $('.xk-xx .r-tbody').html(html);
    })

    $('.r-bzr-ckxsxx').hide();
    $('.find-stuname-div').hide();
    $('.xk-xx').show();
  })
  function stu_xuanke() {
    layui.config({
      base: './'
    }).extend({
      selectN: './js/selectN',
    }).use(['selectN', 'form', 'element'], function () {

      var form = layui.form,
        select = layui.selectN,
        element = layui.element;
      $.post('pjsys/findAllSubject.action', function (data) {
        var catIns = select({
          elem: '#cat_ids',
          selected: [],
          last: false
        });

        var optionstring = "";
        $.each(data, function (i, item) {
          optionstring += "<option value=\"" + item.id + "\" >" + item.subject + "</option>";
        });
        $("#cat_ids").html('<option value=""></option>' + optionstring);
        form.render('select'); //这个很重要
      })

      form.on('select(catids)', function (data) {
        $.post('pjsys/findBySubjectAndAges.action', {
          stu_id: stu_id_index,
          subject_id: data.value
        }, function (datas) {
          var optionstring = "";
          $.each(datas, function (i, item) {
            optionstring += "<option value=\"" + item.id + "\" >" + item.classname + "</option>";
          });
          $("#city").html('<option value=""></option>' + optionstring);
          form.render('select'); //这个很重要
        })
      })
    })
  }


  // .nav-bzr-ckxsxx

  //==========学生管理之选课返回
  $('.xk-return').on('click', function () {
    $('.xk-xx').hide();
    $('.xk-xx .r-tbody').html(' ');
    $('.r-bzr-ckxsxx').show();
    $('.student-msg').show();
  })
  //==========学生管理之删除
  $('.xk-xx').on('click', '.xk-del', function () {

    $.post('pjsys/deleteById.action', {
      id: $(this).attr('indexid')
    }, function (data) {
      if (data === 1) {
        layer.msg('删除成功', {
          time: 1000
        }, function () {
          $.post('pjsys/findInfoById.action', {
            stu_id: stu_id_index
          }, function (data) {

            var html;
            for (var i = 0; i < data.length; i++) {

              var ms_text;
              if (data[i].classinfo.subject.interview == 1) {
                ms_text = '需要面试'
              } else {
                ms_text = '不需要面试'
              }
              var flag_text;
              if (data[i].flag === 1) {
                flag_text = '已报名未缴费';
              } else if (data[i].flag === 2) {
                flag_text = '申请缴费';
              } else if (data[i].flag === 3) {
                flag_text = '已缴费';
              } else if (data[i].flag === 4) {
                flag_text = '退班';
              } else if (data[i].flag === 5) {
                flag_text = '申请退费';
              } else if (data[i].flag === 6) {
                flag_text = '已退费';
              } else if (data[i].flag === 1) {
                flag_text = '申请续费';
              } else if (data[i].flag === 8) {
                flag_text = '已续费';
              } else if (data[i].flag === 10) {
                flag_text = '拒绝缴费';
              } else if (data[i].flag === 11) {
                flag_text = '拒绝续费';
              } else if (data[i].flag === 12) {
                flag_text = '拒绝退费';
              }
              html += '<tr class="r-tbody-tr"><td>' + data[i].classinfo.subject.subject + '</td><td>' + data[i].classinfo.classname + '</td><td>' + ms_text + '</td><td>' + flag_text + '</td><td><span class="del-bg xk-del" indexid="' + data[i].id + '" >删除</span></td></tr>';

            }

            $('.xk-xx .r-tbody').html(html);
          });
        });
      } else {
        layer.msg('修改失败');
      }

    })


  })
  //============学生管理之选课分班
  $('.xk-fb').on('click', function () {

    //  $('.xk-xx .r-tbody').html(' ');
    $('.fade-xkfb .sutname').val($('.xk-xx .r-wenzi span').eq(1).text())

    $('.fade-xkfb').show();


  })
  //======学生管理选课分班之取消
  $('.btn-xkfb-del').on('click', function () {
    $('.fade-xkfb').hide();
  })
  //======学生管理选课分班之确定
  $('.btn-xkfb').on('click', function () {
    if ($('#city').val() == '') {
      layer.msg('班级不能为空');
      return false;
    }
    $.post('pjsys/selectSubject.action', {
      stu_id: stu_id_index,
      classinfo_id: $('#city').val()
    }, function (data) {
      if (data === 1) {
        layer.msg('恭喜选班成功', {
          time: 1000
        }, function () {
          $.post('pjsys/findInfoById.action', {
            stu_id: stu_id_index
          }, function (data) {
            var html;
            for (var i = 0; i < data.length; i++) {
              var ms_text;
              if (data[i].classinfo.subject.interview == 1) {
                ms_text = '需要面试'
              } else {
                ms_text = '不需要面试'
              }
              var flag_text;
              if (data[i].flag === 1) {
                flag_text = '已报名未缴费';
              } else if (data[i].flag === 2) {
                flag_text = '申请缴费';
              } else if (data[i].flag === 3) {
                flag_text = '已缴费';
              } else if (data[i].flag === 4) {
                flag_text = '退班';
              } else if (data[i].flag === 5) {
                flag_text = '申请退费';
              } else if (data[i].flag === 6) {
                flag_text = '已退费';
              } else if (data[i].flag === 1) {
                flag_text = '申请续费';
              } else if (data[i].flag === 8) {
                flag_text = '已续费';
              } else if (data[i].flag === 10) {
                flags = '已拒绝缴费';
              } else if (data[i].flag === 11) {
                flags = '已拒绝续费';
              } else if (data[i].flag === 12) {
                flags = '已拒绝退费';
              }
              html += '<tr class="r-tbody-tr"><td>' + data[i].classinfo.subject.subject + '</td><td>' + data[i].classinfo.classname + '</td><td>' + ms_text + '</td><td>' + flag_text + '</td><td><span class="del-bg xk-del" indexid="' + data[i].id + '" >删除</span></td></tr>';
            }

            $('.xk-xx .r-tbody').html(html);

            $('.fade-xkfb').hide();
          });

        })
      } else {
        layer.msg('选班失败');
      }
    })
  })

  //----------学生管理结束----------


  //----------学生考勤数据开始----------
  $('.nav-kq').on('click', function (e) {
    stu_kaoqin()
    navkq('.r-jw-kq .r-wenzi select[name="kq-class"]')
    $('.r-jw-kq').show().siblings().hide();
    e.stopPropagation()
  })
  //班级列表显示  navkq() 接收类名
  // function navkq() 
  function navkq(classname) {
    $.post('/pjsys/findAllClassinfos.action', function (data) {
      // console.log(data);
      var html = '<option value=""></option>';
      for (var i = 0; i < data.length; i++) {
          html += '<option value="' + data[i].id + '">' + data[i].classname + '</option>'
      }
      // $('.r-jw-kq .r-wenzi select[name="kq-class"]').html(html)
      $(classname).html(html)
      form.render('select'); //这个很重要
    })
  }

  // 切换班级
  form.on('select(xskq)', function (data) {
    stu_kaoqin(data.value)
  });
  //列表显示考勤
  function stu_kaoqin(classinfo_id) {
    var classinfo_id = $('.r-jw-kq select[name="kq-class"]').val();
    $.post('$pjsys/attendancesummary.action', {
      page: 1,
      classinfo_id: classinfo_id
    }, function (data) {
      page = data.total;
      laypage.render({
        elem: 'jw-student-attendance-summary',
        count: page,
        theme: '#65daf7',
        first: '首页',
        last: '尾页',
        jump: function (obj, first) {
          $.post('/pjsys/attendancesummary.action', {
            page: obj.curr,
            classinfo_id: classinfo_id
          }, function (data) {
            // $('.r-jw-kq .r-table .r-tbody').html('');
            var html_from = '';
            var flag = ''
            var matter = ''
            if (data.datas == null || data.datas == '' || data.datas.length<=0) {
              html_from = '<tr class="r-tbody-tr"><td colspan="5">当前暂无学生考勤数据</td></tr>';
            } else {
              for (var i = 0; i < data.datas.length; i++) {
                if (data.datas[i][3] === 1) {
                  flag = '正常'
                } else if (data.datas[i][3] === 2) {
                  flag = '缺勤'
                } else {
                  flag = '考勤异常'
                }
                if (data.datas[i][4] == null || data.datas[i][4] == '') {
                  matter = '无'
                } else {
                  matter = data.datas[i][4]
                }
                html_from += '<tr class="r-tbody-tr">\\n\' +\n' +
                  '<td>' + data.datas[i][0] + '</td>' +
                  '<td>' + data.datas[i][1] + '</td>' +
                  '<td>' + data.datas[i][2] + '</td>' +
                  '<td>' + flag + '</td>' +
                  // '<td>'+data.datas[i][3]+'</td>'+
                  '<td>' + matter + '</td>' +
                  // '<td>'+data.datas[i][4]+'</td>'+
                  '</tr>';
              }
            }

            $('.r-jw-kq .r-table .r-tbody').html(html_from);
            form.render('select');
          })
        }
      })
    })
  }
    //----------学生考勤数据结束----------
  //----------------------------------------------
  // 班级考勤统计
    $('.nav-classKq').on('click', function (e) {
      $('.r-jw-classKq').show().siblings().hide();
      $('#time-search-kq').val('')
      attendance()
      e.stopPropagation()
    })
    // 默认班级考勤统计列表显示
    function attendance () {
      $.post('/pjsys/queryClassinfoInAttence',{pageNum:1,pageSize:10},function(data){
        laypage.render({
        elem: 'jw-class-attendance-summary'
        ,count:data.total
        ,theme: '#65daf7'
        ,first: '首页'
        ,last: '尾页'
        ,jump: function(obj){
          $.post('/pjsys/queryClassinfoInAttence',{pageNum:obj.curr,pageSize:10},function(data){
            var html = ''
            for(var i=0;i<data.datas.length;i++){
              html += '<tr class="r-tbody-tr"><td>' + data.datas[i].classname + '</td><td>' + data.datas[i].count + '</td><td>' + data.datas[i].onPerson + '</td><td>' + data.datas[i].unPerson + '</td><td>' + data.datas[i].notPerson +'</td><td><span class="xg-bg class-reason" index_id = "'+data.datas[i].id+'">具体缺勤学生</span></td></tr>'
            }
            $('.r-jw-classKq .r-table .r-tbody').html(html)
          })
        }
      })
      })
    }
  // 班级具体缺勤原因
    $('body').on('click', '.r-jw-classKq .r-table .class-reason',function(){
      var time = $('#time-search-kq').val()
      absenceReason($(this).attr("index_id"), time);
    })
    // 缺勤原因
  function absenceReason(id,time) {
    var html = ''
    if (time === '') {
    $.post('/pjsys/unAttenceMessage', {
      classinfo_id: id
    }, function (data) {
      for (var i = 0; i < data.length; i++) {
        html += '<div class="layui-form"><table class = "layui-table"> <tr> <tbody><td>' + data[i].student.stuName + '</td><td>' + data[i].reasons.reason + '</td></tbody></tr></table></div>'
      }
      layer.open({
        type: 1,
        title: '具体缺勤学生详情',
        skin: 'layui-layer-molv', //加上边框
        area: ['800px', '400px'], //宽高
        content: html
      });
    })
    } else {
      $.post('/pjsys/unAttenceMessage', {
            classinfo_id: id,
            dateTime: time
          }, function (data) {
      for (var i = 0; i < data.length; i++) {
        html += '<div class="layui-form"><table class = "layui-table"> <tr> <tbody><td>' + data[i].student.stuName + '</td><td>' + data[i].reasons.reason + '</td></tbody></tr></table></div>'
      }
      layer.open({
        type: 1,
        title: '具体缺勤学生详情',
        skin: 'layui-layer-molv', //加上边框
        area: ['800px', '400px'], //宽高
        content: html
      });
    })
    }
  }
// 班级考勤按照班级查询
  $("body").on("click", ".r-jw-classKq .search-class-kq",function(e){
    e.stopPropagation()
    var className = $('.search-kq-class-name').val()
    if (className == ''){
      layer.msg('请输入班级名')
    }else{
      $(".fade-search-className").show();
      $.post("/pjsys/queryClassinfoInAttence", { pageNumb: 1, pageSize:1, my_classname: className},function(data){
        var html = "";
        if(data.datas != ''){
          for (var i = 0; i < data.datas.length; i++) {
            html += '<tr class="r-tbody-tr"><td>' + data.datas[i].classname + '</td><td>' + data.datas[i].count + '</td><td>' + data.datas[i].onPerson + '</td><td>' + data.datas[i].unPerson + '</td><td>' + data.datas[i].notPerson + '</td><td><span class="xg-bg class-reason" index_id = "' + data.datas[i].id + '">查看原因</span></td></tr>'
          }
        }else{
          html = '<tr class="r-tbody-tr"><td>暂无数据</td></tr>'; 
        }
        $(".fade-search-className .r-tbody").html(html);
      });
    }
  });
  // 查询的具体缺勤原因
  $('body').on('click', '.fade-search-className .r-table .class-reason', function () {
    absenceReason($(this).attr("index_id"));
  })
  $("body").on("click",".fade-search-className .btn-left",function() {
      $(".fade-search-className").hide();
    }
  );
  // 班级考勤按照时间显示
  laydate.render({
    elem: '#time-search-kq'
  });
  // 选择时间之后点击
  $('.search-time-kq').click(function(){
    $('.r-jw-classKq .r-table .r-tbody').html()
    var time = $('#time-search-kq').val()
    if (time === '') {
      layer.msg('请选择时间')
    } else {
      loading("数据加载中，请稍等！", false);
      $.post('/pjsys/queryClassinfoInAttence', {
      pageNum: 1,
      pageSize: 10,
      dateTime: time
    },function(data){
      loading("加载成功", true);
      laypage.render({
        elem: 'jw-class-attendance-summary',
        count: data.total,
        theme: '#65daf7',
        first: '首页',
        last: '尾页',
        jump: function (obj) {
          $.post('/pjsys/queryClassinfoInAttence', {
            pageNum: obj.curr,
            pageSize: 10,
            dateTime: time
          }, function (data) {
            var html = ''
            for (var i = 0; i < data.datas.length; i++) {
              html += '<tr class="r-tbody-tr"><td>' + data.datas[i].classname + '</td><td>' + data.datas[i].count + '</td><td>' + data.datas[i].onPerson + '</td><td>' + data.datas[i].unPerson + '</td><td>' + data.datas[i].notPerson + '</td><td><span class="xg-bg class-reason" index_id = "' + data.datas[i].id + '">具体缺勤学生</span></td></tr>'
            }
            $('.r-jw-classKq .r-table .r-tbody').html(html)
          })
        }
      })
    })
    }
  })
//----------------------------------

  //----------数据汇总开始----------
  $('.nav-sjhz').on('click', function (e) {
    sjhz()
    $('.r-jw-sjhz').show().siblings().hide();
    e.stopPropagation()
  })
  // 获取数据汇总
  function sjhz() {
    $.post('./pjsys/findAll.action', {
      user_id: user_id
    }, function (data) {
      //  console.log(data);
      var htm;
      htm += '<tr class="r-tbody-tr">+<td>已注册人数</td>+<td>' + data.student + '</td></tr><tr class="r-tbody-tr"><td>在读人数</td><td>' + data.studentOf + '</td></tr><tr class="r-tbody-tr"><td>科目总数</td><td>' + data.subject + '</td></tr><tr class="r-tbody-tr"><td>班级总数</td><td>' + data.classinfo + '</td></tr>';
      $('#idd').html(htm)
    })
  }


  element.on('tab(kaoqin)', function (data) {
    if ($(this).index() == 1) {
      //  男女比例
      $.post('/pjsys/countpercent.action', function (data) {
        // console.log(data);
        var html;
        html += '<tr class="r-tbody-tr">+<td>舞蹈</td>+<td>' + data.first + '</td></tr><tr class="r-tbody-tr"><td>器乐</td><td>' + data.second + '</td></tr><tr class="r-tbody-tr"><td>声乐</td><td>' + data.three + '</td></tr><tr class="r-tbody-tr"><td>美术</td><td>' + data.four + '</td></tr>' + '</td></tr><tr class="r-tbody-tr"><td>体育</td><td>' + data.five + '</td></tr>' + '</td></tr><tr class="r-tbody-tr"><td>文学</td><td>' + data.six + '</td></tr>' + '</td></tr><tr class="r-tbody-tr"><td>英语</td><td>' + data.seven + '</td></tr>' + '</td></tr><tr class="r-tbody-tr"><td>合作办学</td><td>' + data.eight + '</td></tr>';
        $('#proportion').html(html)
      })
    }
  })

  //----------数据汇总结束----------


  //----------站内通知开始----------
  // 站内通知
  $('.nav-news').click(function (e) {
    $('.r-jw-news').show().siblings().hide();
    e.stopPropagation()
  })
  // 全体发送消息
  $('.btn-send').click(function () {
    var header = $('.news-title1').val();
    var message = $('.news-message1').val()
    if (header == '' || message == "") {
      layer.msg('请输入内容或标题', {
        time: 2000
      })
    } else {
      $.post('/pjsys/addMessage.action', {
        teacher_id: user_id,
        'imessage.head': header,
        'imessage.message': message
      }, function (data) {
        if (data === 1) {
          layer.msg('成功发送消息', {
            time: 2000
          });
          $('.news-title1').val('')
          $('.news-message1').val('')
        } else {
          layer.msg("请尝试重新发送", {
            time: 2000
          })
        }
      })
    }
  })
  // 班级发送消息
  $('.btn-send-class').on('click', function () {
    var header = $('.news-header-class').val();
    var message = $('.news-message-class').val();
    var is = [];
    var iss = [];
    for (var i = 0; i < $('.transfer-list-right .ty-tree-select li').length; i++) {
      is.push(i)
    }
    for (var i = 0; i < is.length; i++) {
      var j = $('.transfer-list-right .ty-transfer-list-body .ty-tree-div .tyue-checkbox-txt').eq(i).attr('data-id')
      iss.push(j)
    }
    iss = iss.join(',')
    if (header == '' || message == '') {
      layer.msg('请输入内容', {
        time: 2000
      })
    } else if (iss == []) {
      layer.msg('已选班级列表中至少要有一个班级', {
        time: 2000
      })
    } else {
      $.post('/pjsys/addimessageByClass.action', {
        classinfo_ids: iss,
        'imessageByClass.head': header,
        'imessageByClass.message': message,
        teacher_id: user_id
      }, function (data) {
        if (data == 1) {
          layer.msg('发送成功', {
            time: 2000
          })
          $('.news-header-class').val('')
          $('.news-message-class').val('')
        } else {
          layer.msg('失败,请退出重新登录', {
            time: 2000
          })
        }
      })
    }
  })
  // 面试发送消息
  $('.btn-send-interview').click(function () {
    var header = $('.news-header-interview').val()
    var message = $('.news-message-interview').val()
    if (header == '' || message == '') {
      layer.msg('请输入内容', {
        time: 2000
      })
    } else {
      $.post('/pjsys/addImessageByInter.action', {
        'imessageByInter.head': header,
        'imessageByInter.message': message,
        teacher_id: user_id
      }, function (data) {
        if (data == 1) {
          layer.msg('发送成功', {
            time: 2000
          })
          $('.news-header-interview').val('')
          $('.news-message-interview').val('')
        } else {
          layer.msg('发送失败,请尝试重新发送', {
            time: 2000
          })
        }
      })
    }
  })
  // 监听tab切换
  element.on('tab(news)', function (data) {
    if ($(this).index() == 1) {
      // 获取班级列表
      $("#ued-transfer-1").transferItem();
      var total = 10
      $.post('pjsys/findAllClassinfo.action', {
        page: 1
      }, function (data) {
        total = data.total;
        laypage.render({
          elem: 'news-page',
          count: total,
          theme: "#65daf7",
          layout: ['prev', 'next'],
          jump: function (obj, first) {
            if (!first) {
              layer.msg('第 ' + obj.curr + ' 页');
            }
            $.post('/pjsys/findAllClassinfo.action', {
              page: obj.curr
            }, function (data) {
              var html = ''
              for (var i = 0; i < data.datas.length; i++) {
                html += '<li><div class="ty-tree-div"><label class="tyue-checkbox-wrapper"><span class="tyue-checkbox"><input type="checkbox" class="tyue-checkbox-input"><span class="tyue-checkbox-circle"></span></span><span class="tyue-checkbox-txt" data-id="' + data.datas[i].id + '">' + data.datas[i].classname + '</span></label></div></li>'
              }
              $('.transfer-list-left .ty-tree-select').html(html)
            })
          }
        })
      });
    }
    if ($(this).index() == 3) {
      notice()
    }
  })

  // 班级多选
  // 选中事件
  $('body').on('click', '.r-jw-news .layui-tab-content .r-table .r-thead .r-thead-tr th input[type="checkbox"]', function () {
    $('.r-jw-news .r-table .r-tbody .r-tbody-tr td input[type="checkbox"]').prop("checked", $(this).prop("checked"));
  });
  
  // 开学通知
  $('body').on('click', '.updateNews',function(){
    var html = $('.add-news-header').val();
    if(html == ''){
      layer.msg('请输入开学通知头部信息')
    }else{
      $.post('/pjsys/submitNotice', {
        content: html
      },function(data){
        if(data === 1){
          layer.msg('成功更新开学通知')
          $('.add-news-header').val('')
        }else{
          layer.msg('更新失败')
        }
      })
    }
  })
  // 开学通知显示
  function notice(){
    $.post('/pjsys/firstNoticeMessage',function(data){
      $('.add-news-header').attr('placeholder', data.content)
    })
  }
  //----------站内通知结束----------


  // 学习日历管理
  $('.nav-calendar').click(function(e){
    $('.r-jw-calendar').show().siblings().hide();
    e.stopPropagation()
  })
  // 上传
  var uploadInst = upload.render({
    elem: '#test1'
    // 上传接口
    , url: '/pjsys/pictureUpLoad'
    // 限制上传格式
    , accept: 'file'
    , exts:'png|jpg|jpeg'
    , data: {
      teacher_id: user_id
    }
    , before: function (obj) { //obj参数包含的信息，跟 choose回调完全一致，可参见上文。
      obj.preview(function (index, file, result) {
        $('#jw-calendar').append('<img src="' + result + '" alt="' + file.name + '" class="layui-upload-img">')
      });
      layer.load(); //上传loading
    }
    , done: function () {
      layer.closeAll("loading");
      layer.msg('成功上传图片',{time:2000})
    }
    , error: function () {
      layer.closeAll('loading');
      layer.msg('失败,请重新上传',{time:2000})
      
    }
  });
  //================教务端结束============

  //================班长任端开始==========
  //加载层
  function loading(msg,is) {
    layer.msg(msg, {
      icon: 16,
      shade: [0.1, '#fff'],
      time: is  //取消自动关闭
    })
  }
  // ====更新字典表
  $('body').on('click','#push',function() {
    loading("数据提交中，请稍等！", false);
    $.post('/pjsys/updateAllDictionary', function (data) {
      if(data === 1) {
        loading("数据提交中，请稍等！", true);
        layer.msg('更新成功')
      } else {
        loading("数据提交中，请稍等！", true);
        layer.msg('更新失败请重新尝试')
      }
    })
  })
  // 单个学生查找考勤
  $('#teacher-search-btn').click(function(){
        var option_hmtl = '<option value=""></option>';
        // 考勤原因
        $.post('pjsys/findAllReasons.action', function (data) {
          for (var i = 0; i < data.length; i++) {
            option_hmtl += '<option value="' + data[i].id + '">' + data[i].reason + '</option>'
          }
        });
    var name = $('#teacher-search').val()
    if(name === '') {
      layer.msg('请最少输入一个字符')
    } else {
        $.post('pjsys/spellFindByClassinfoId', {
          spell_stu_name: name,
          page: 1
        }, function (data) {
          page = data.total;
          laypage.render({
            elem: 'teacher-search-page',
            count: page,
            theme: "#65daf7",
            jump: function (obj, first) {
              // $.post('pjsys/findByClassinfoId.action',{classinfo_id:classinfo_id,page:obj.curr},function (data) {
              $.post('pjsys/spellFindByClassinfoId', {
                spell_stu_name: name,
                page: obj.curr
              }, function (data) {
                var html = '';
                if (data.datas == null || data.datas == '') {
                  $('.r-bzr-xskq .r-tbody-search').html('<tr class="r-tbody-tr"><td colspan="6">暂无当前搜索学生数据,请查看该学生是否已经考过勤</td></tr>');
                  return false;
                } else {
                  for (var i = 0; i < data.datas.length; i++) {
                    var sex;
                    if (data.datas[i].student.sex == 1) {
                      sex = '男';
                    } else {
                      sex = '女';
                    }
                    html += '<tr class="r-tbody-tr-sel">\n' +
                      '                        <td>' + data.datas[i].student.stuName + '</td>\n' +
                      '                        <td>' + sex + '</td>\n' +
                      '                        <td>' + data.datas[i].student.personalNum + '</td>\n' +
                      '                        <td>' + data.datas[i].classinfo.classname + '</td>\n' +
                      '                        <td class="layui-form">\n' +
                      '                        <div class="layui-form-item">\n' +
                      '                        <select name="miss" lay-verify="required">\n' + option_hmtl +
                      '                        </select>\n' +
                      '                        </div>\n' +
                      '                        </td>\n' +
                      '                        <td><span class="xg-bg" stu-id="'+data.datas[i].student.id+'" class_id="'+data.datas[i].classinfo.id+'">正常</span><span class="del-bg" stu-id="'+data.datas[i].student.id+'" class_id="'+data.datas[i].classinfo.id+'">缺勤</span></td>\n' +
                      '                    </tr>';
                  }
                  $('.r-bzr-xskq .r-tbody-search').html(html);
                  form.render('select');
                }
              })
            }
          });
        })
    }
  })
  // 正常考勤
  $('body').on('click','.r-bzr-xskq .r-tbody-search .xg-bg',function(){
    var id = $(this).attr('stu-id')
    var classinfo_id = $(this).attr('class_id')
    console.log(id)
    $.post('pjsys/attence.action', {
        stu_id: id,
        attence_status: 1,
        classinfo_id: classinfo_id,
        reason_ids: 0
      }, function (data) {
        if (data == 1) {
          layer.msg('操作成功', {
            time: 1000
          }, function () {
            $('#teacher-search-btn').click()
          });
        } else {
          layer.msg('操作失败');
        }
      });
  })
  // 缺勤
  $('body').on('click','.r-bzr-xskq .r-tbody-search .del-bg',function(){
    var id = $(this).attr('stu-id')
    var classinfo_id = $(this).attr('class_id')
    var reas = 0
    reas = $(this).parent().prev().find('select').val()
    console.log($(this).parent())
    console.log(reas)
    if(reas === 0 || reas === null ||  reas === '') {
      layer.msg('请选择缺勤原因')
    }else {
          $.post('pjsys/attence.action', {
        stu_id: id,
        attence_status: 2,
        classinfo_id: classinfo_id,
        reason_ids: reas
      }, function (data) {
        if (data == 1) {
          layer.msg('操作成功', {
            time: 1000
          }, function () {
            $('#teacher-search-btn').click()
          });
        } else {
          layer.msg('操作失败');
        }
      });
    }
  })
  function stu_data(classinfo_id) {
    var classinfo_id = $('.r-bzr-xskq select[name="kq-sel"]').val();
    NAME(classinfo_id);
    stuNum(classinfo_id);
    var page = 1;
  }
  // 考勤统计人数
  function stuNum(id){
    // 缺勤
    $.post('/pjsys/CountUnAttence.action', { classinfo_id: id }, function (data) {
      $('.r-bzr-xskq .CountAttences').val(data)
    })
    // 正常
    $.post('/pjsys/CountAttences.action', { classinfo_id: id }, function (data) {
      $('.r-bzr-xskq .CountUnAttence').val(data)
    })
  }
  // 考勤学生列表
  function NAME (id) {
    var option_hmtl = '<option value=""></option>';
    // 考勤原因
    $.post('pjsys/findAllReasons.action', function (data) {
      for (var i = 0; i < data.length; i++) {
        option_hmtl += '<option value="' + data[i].id + '">' + data[i].reason + '</option>'
      }
    });
    $.post('pjsys/findByclassId1.action', { classinfo_id: id, page: 1 }, function (data) {
      page = data.total;
      laypage.render({
        elem: 'student-attendance' 
        ,
        count: page 
        ,
        theme: "#65daf7",
        jump: function (obj, first) {
          // $.post('pjsys/findByClassinfoId.action',{classinfo_id:classinfo_id,page:obj.curr},function (data) {
          $.post('pjsys/findByclassId1.action', {
            classinfo_id: id,
            page: obj.curr
          }, function (data) {
            var html = '';
            if (data.datas == null || data.datas == '') {
              $('.r-bzr-xskq .r-tbody-stu').html('<tr class="r-tbody-tr"><td colspan="6">当前班级已经考勤完毕</td></tr>');
              return false;
            } else {
              for (var i = 0; i < data.datas.length; i++) {
                var sex;
                if (data.datas[i].student.sex == 1) {
                  sex = '男';
                } else {
                  sex = '女';
                }
                html += '<tr class="r-tbody-tr-sel">\n' +
                  '                        <td ><input type="checkbox" name=""  data-id="' + data.datas[i].id + '"  data-student-id="' + data.datas[i].student.id + '" data-bj-id="' + data.datas[i].classinfo.id + '" ></td>\n' +
                  '                        <td>' + data.datas[i].student.stuName + '</td>\n' +
                  '                        <td>' + sex + '</td>\n' +
                  '                        <td>' + data.datas[i].student.personalNum + '</td>\n' +
                  '                        <td>' + data.datas[i].classinfo.classname + '</td>\n' +
                  '                        <td class="layui-form">\n' +
                  '                        <div class="layui-form-item">\n' +
                  '                        <select name="miss" lay-verify="required">\n' + option_hmtl +
                  '                        </select>\n' +
                  '                        </div>\n' +
                  '                        </td>\n' +
                  '                    </tr>';
              }
              $('.r-bzr-xskq .r-tbody-stu').html(html);
              form.render('select'); //这个很重要
              form.render('checkbox'); //这个很重要
            }
          })
        }
      });
    })
  }
  var stu_data_index;
  // 考勤班级列表
  function kqclasslist() {
    $.post('/pjsys/findAllClassinfos.action', function (data) {
      var html = '<option value=""></option>';
      for (var i = 0; i < data.length; i++) {
          html += '<option value="' + data[i].id + '">' + data[i].classname + '</option>'
      }
      $('.r-bzr-xskq .r-wenzi select[name="kq-sel"]').html(html)
      form.render('select'); //这个很重要
    })
  }
  $('.nav-bzr-xskqgl').on('click', function (e) {
    kqclasslist()
    stu_data(stu_data_index)
    $('.r-bzr-xskq').show().siblings().hide();
    e.stopPropagation()
  })
  // 选中事件
  $('body').on('click', '.r-bzr-xskq .r-table .r-thead .r-thead-tr th input[type="checkbox"]', function () {
    $('.r-bzr-xskq .r-table .r-tbody .r-tbody-tr-sel td input[type="checkbox"]').prop("checked", $(this).prop("checked"));
  });
  form.on('select(xskcgl)', function (data) {
    stu_data(data.value)
  });
  // 班主任考勤
  $('.r-bzr-xskq .r-btn .r-btn-kq').click(function () {
    var tr_len = $('.r-bzr-xskq .r-tbody .r-tbody-tr-sel').length;
    var index = $(this).index()
    var state = $(this).attr('state') //考勤状态   1为正常  2为缺勤
    var classinfo_id; //班级id
    var reason_id=[];
    var reason=[];
    var id_arr = [];
    // 多选添加
    for (var i = 0; i < tr_len; i++) {
      if ($('.r-bzr-xskq .r-tbody .r-tbody-tr-sel').eq(i).find('td input[type="checkbox"]').is(':checked')) {
        reason_id.push($('.r-bzr-xskq .r-tbody .r-tbody-tr-sel').eq(i).find('td select').val())
        reason.push(0)
        classinfo_id = $('.r-bzr-xskq .r-tbody .r-tbody-tr-sel').eq(i).find('td input[type="checkbox"]').attr('data-bj-id')
        id_arr.push($('.r-bzr-xskq .r-tbody .r-tbody-tr-sel').eq(i).find('td input[type="checkbox"]').attr('data-student-id'))
      }
    }
    id_arr = id_arr.join(',');
    reason_id = reason_id.join(',');
    reason = reason.join(',');
    if (id_arr == '') {
      layer.msg('请选择学生,最少可以选择一个学生');
      return false;
    }
    // 正常考勤
    if (index == 0) {
      $.post('pjsys/attence.action', {
        stu_id: id_arr,
        attence_status: state,
        classinfo_id: classinfo_id,
        reason_ids: reason
      }, function (data) {
        if (data == 1) {
          layer.msg('操作成功', {
            time: 1000
          }, function () {
            // $('.nav-bzr-xskqgl').click();
            NAME(classinfo_id);
            stuNum(classinfo_id);
            // $('.r-bzr-xskq select option[value="' + classinfo_id + '"]').click()
          });
        } else {
          layer.msg('操作失败');
        }
      });
    }
    // 缺勤
    if (index == 1) {
      if (reason_id == 0) {
        layer.msg('请选择缺勤原因');
        return false;
      }
      $.post('pjsys/attence.action', {
        stu_id: id_arr,
        attence_status: state,
        classinfo_id: classinfo_id,
        reason_ids: reason_id
      }, function (data) {
        if (data == 1) {
          layer.msg('操作成功', {
            time: 1000
          }, function () {
            NAME(classinfo_id);
            stuNum(classinfo_id);
          });
        } else {
          layer.msg('操作失败');
        }
      })
    }
  })

  // 修改考勤
  $('body').on('click', '#teacher-search-stu',function() {
    if ($('#teacher-search-input').val() === '') {
      layer.msg('请输入学生姓名')
    } else {
    var option_hmtl = '<option value=""></option>';
        // 考勤原因
        $.post('pjsys/findAllReasons.action', function (data) {
          for (var i = 0; i < data.length; i++) {
            option_hmtl += '<option value="' + data[i].id + '">' + data[i].reason + '</option>'
          }
      });
      var html = ''
      var flag = ''
      var reason = ''
      var sex = ''
      var stuName = $('#teacher-search-input').val()
      $.post('pjsys/showAttence.action', {stuName: stuName}, function (data) {
        for(var i= 0 ;i<data.length;i++){
          flag = (data[i].attence_status == 1) ? '正常':  '缺勤';
          reason = (data[i].reasons == null || data[i].reasons == '') ? '无' : data[i].reasons.reason
          sex = (data[i].student.sex == 1) ? '男' : '女';
          html += '<tr class="r-tbody-tr-sel">\n' +
                      '                        <td>' + data[i].attence_time + '</td>\n' +
                      '                        <td>' + data[i].student.stuName + '</td>\n' +
                      '                        <td>' + sex + '</td>\n' +
                      '                        <td>' + data[i].student.personalNum + '</td>\n' +
                      '                        <td>' + data[i].classinfo.classname + '</td>\n' +
                      '                        <td>' + flag + '</td>\n' +
                      '                        <td>' + reason + '</td>\n' +
                      '                        <td class="layui-form">\n' +
                      '                        <div class="layui-form-item">\n' +
                      '                        <select name="missstu" lay-verify="required">\n' + option_hmtl +
                      '                        </select>\n' +
                      '                        </div>\n' +
                      '                        </td>\n' +
                      '                        <td><span class="xg-bg" stu-id="'+data[i].id+'" class_id="'+data[i].classinfo.id+'">正常</span><span class="del-bg" stu-id="'+data[i].id+'" class_id="'+data[i].classinfo.id+'">缺勤</span></td>\n' +
                      '                    </tr>';
        }
        $('.r-bzr-xskq .r-tbody-search-stu').html(html);
        form.render('select');
      })
    }
  })
  // 修改为正常考勤
$('body').on('click', '.r-bzr-xskq .r-tbody-search-stu .xg-bg',function(){
  console.log($(this).attr('stu-id'));
  XGzckq($(this).attr('stu-id'))
})
  function XGzckq (vals ) {
    $.post('/pjsys/updateAttence.action',{id:vals},function(data){
      if(data==1){
        layer.msg('修改成功',{timer:2000})
        $('#teacher-search-stu').click()
      }else{
        layer.msg('修改失败',{timer:2000})
      }
    })
  }
// 修改为缺勤
$('body').on('click', '.r-bzr-xskq .r-tbody-search-stu .del-bg', function () {
  var ids = $(this).attr('stu-id')
  var reason_id = $(this).parent().prev().find('select').val()
  var attence_status = 2
  if (reason_id === '' || reason_id == null) {
    layer.msg('请按照规则操作,选择原因')
  } else {
    $.post('/pjsys/attenceUpdateStatu.action', {
      id: ids,
      attence_status: attence_status,
      reason_id: reason_id
    }, function (data) {
      if (data === 1) {
        layer.msg('成功修改')
        $('#teacher-search-stu').click()
      } else {
        layer.msg('失败')
      }
    })
  }
})
  // 获取当前时间
  var myDate = new Date();
  var mydata = myDate.toLocaleDateString();
  $('.r-bzr-xskq .layui-tab-content h3').html(mydata)
  //----------学生考勤结束----------
  //================班长任端结束==========


  //================财务端开始==========
  //----------缴费审核开始----------
  // -----缴费审核
  $('.nav-cw-jfsh').on('click', function (e) {
    var first_page = 10; //得到每页显示的条数
    var first_total = 10; //总数据条数
    $.post('/pjsys/findAllInfo4.action', {
      page: 1
    }, function (data) {
      first_total = data.total;
      $('.r-cw-jfsh .r-cw-jfsh-old .r-table .r-tbody').html('');
      var frxx_form_hmtl = '';
      for (var i = 0; i < data.datas.length; i++) {
        var flag = '';
        var time = '';
        if (data.datas[i].flags == null) {
          flag = '未备注';
        } else {
          flag = data.datas[i].flags;
        }
        if (data.datas[i].s_j_time === '' || data.datas[i].s_j_time === null) {
          time = '暂无申请时间'
        } else {
          time = data.datas[i].s_j_time
        }
        if (user_role == 2 || user_role == 4) {
          frxx_form_hmtl += '<tr class="r-tbody-tr">\n' +
            '                        <td>\n' +
            '                            <input type="checkbox" name="" stuid = "'+data.datas[i].student.id+'" useid="'+data.datas[i].student.user.id+'" sxid="' + data.datas[i].id + '"  >\n' +
            '                        </td>\n' +
            '                        <td>' + time + '</td>\n' +
            '                        <td>' + data.datas[i].student.stuName + '</td>\n' +
            '                        <td>' + data.datas[i].student.personalNum + '</td>\n' +
            '                        <td>' + data.datas[i].student.parent + '</td>\n' +
            '                        <td>' + data.datas[i].student.mobilephone + '</td>\n' +
            '                        <td>' + data.datas[i].classinfo.subject.subject + '</td>\n' +
            '                        <td>' + data.datas[i].classinfo.classname + '</td>\n' +
            '                        <td>' + data.datas[i].classinfo.money + '</td>\n' +
            '                        <td>' + flag + '</td>\n' +
            '                        <td><span class="xg-bg btn-class-remark" title="' + flag + '" data-index="' + data.datas[i].id + '" >备注</span></td>\n' +
            '                    </tr>';
        } else {
          frxx_form_hmtl += '<tr class="r-tbody-tr">\n' +
            '                        <td>\n' +
            '                            <input type="checkbox" name="" stuid = "'+data.datas[i].student.id+'" useid="'+data.datas[i].student.user.id+'" sxid="' + data.datas[i].id + '" >\n' +
            '                        </td>\n' +
            '                        <td>' + time + '</td>\n' +
            '                        <td>' + data.datas[i].student.stuName + '</td>\n' +
            '                        <td>' + data.datas[i].student.personalNum + '</td>\n' +
            '                        <td>' + data.datas[i].student.parent + '</td>\n' +
            '                        <td>' + data.datas[i].student.mobilephone + '</td>\n' +
            '                        <td>' + data.datas[i].classinfo.subject.subject + '</td>\n' +
            '                        <td>' + data.datas[i].classinfo.classname + '</td>\n' +
            '                        <td>' + data.datas[i].classinfo.money + '</td>\n' +
            '                        <td>' + flag + '</td>\n' +
            '                        <td><span class="xg-bgs" title="' + flag + '" data-index="' + data.datas[i].id + '" >备注</span></td>\n' +
            '                    </tr>';
        }
      }
      $('.r-cw-jfsh .r-cw-jfsh-old .r-table .r-tbody').html(frxx_form_hmtl);
      laypage.render({
        elem: 'pay'
          ,
        count: first_total
          ,
        limit: first_page,
        theme: "#65daf7",
        first: '首页',
        last: '尾页',
        jump: function (obj, first) {
          $.post('/pjsys/findAllInfo4.action', {
            page: obj.curr
          }, function (data) {
            bjlst_page = obj.curr;
            first_page = obj.first;
            var frxx_form_hmtl = '';
            var flag = '';
            var time = '';
            for (var i = 0; i < data.datas.length; i++) {
              if (data.datas[i].flags == null) {
                flag = '未备注';
              } else {
                flag = data.datas[i].flags;
              }
              if (data.datas[i].s_j_time === '' || data.datas[i].s_j_time === null) {
                time = '暂无申请时间'
              } else {
                time = data.datas[i].s_j_time
              }
              if (user_role == 2 || user_role == 4) {
                frxx_form_hmtl += '<tr class="r-tbody-tr">\n' +
                  '                        <td>\n' +
                  '                            <input type="checkbox" name="" stuid = "' + data.datas[i].student.id + '" useid="' + data.datas[i].student.user.id + '" sxid="' + data.datas[i].id + '" >\n' +
                  '                        </td>\n' +
                  '                        <td>' + time + '</td>\n' +
                  '                        <td>' + data.datas[i].student.stuName + '</td>\n' +
                  '                        <td>' + data.datas[i].student.personalNum + '</td>\n' +
                  '                        <td>' + data.datas[i].student.parent + '</td>\n' +
                  '                        <td>' + data.datas[i].student.mobilephone + '</td>\n' +
                  '                        <td>' + data.datas[i].classinfo.subject.subject + '</td>\n' +
                  '                        <td>' + data.datas[i].classinfo.classname + '</td>\n' +
                  '                        <td>' + data.datas[i].classinfo.money + '</td>\n' +
                  '                        <td>' + flag + '</td>\n' +
                  '                        <td><span class="xg-bg btn-class-remark" title="' + flag + '" data-index="' + data.datas[i].id + '" >备注</span></td>\n' +
                  '                    </tr>';
              } else {
                frxx_form_hmtl += '<tr class="r-tbody-tr">\n' +
                  '                        <td>\n' +
                  '                            <input type="checkbox" name="" stuid = "'+data.datas[i].student.id+'" useid="'+data.datas[i].student.user.id+'" sxid="' + data.datas[i].id + '" >\n' +
                  '                        </td>\n' +
                  '                        <td>' + time + '</td>\n' +
                  '                        <td>' + data.datas[i].student.stuName + '</td>\n' +
                  '                        <td>' + data.datas[i].student.personalNum + '</td>\n' +
                  '                        <td>' + data.datas[i].student.parent + '</td>\n' +
                  '                        <td>' + data.datas[i].student.mobilephone + '</td>\n' +
                  '                        <td>' + data.datas[i].classinfo.subject.subject + '</td>\n' +
                  '                        <td>' + data.datas[i].classinfo.classname + '</td>\n' +
                  '                        <td>' + data.datas[i].classinfo.money + '</td>\n' +
                  '                        <td>' + flag + '</td>\n' +
                  '                        <td><span class="xg-bgs" title="' + flag + '" data-index="' + data.datas[i].id + '" >备注</span></td>\n' +
                  '                    </tr>';
              }

            }
            $('.r-cw-jfsh .r-cw-jfsh-old .r-table .r-tbody').html(frxx_form_hmtl);
          })
        }
      })
    })
    $('.r-cw-jfsh').show().siblings().hide();
    e.stopPropagation()
  })
  //缴费审核按姓名查找
  $('body').on('click', '.jf-search-btn', function () {
    $('.r-cw-jfsh-old').hide();
    $('.r-cw-jfsh-new').show();
  })
  $('.r-cw-jfsh .r-cw-jfsh-new .r-wenzi .jf-btn-search').click(function () {

    var stuName = $('#jf-find-name').val()
    if (stuName == '') {
      layer.msg('请输入学生名字');
      return false;
    }
    $.post('/pjsys/show4.action', {
      stuName: stuName
    }, function (data) {
      var frxx_form_hmtl = '';
      var time = '';
      if (data.length == 0) {
        frxx_form_hmtl += '<tr class="r-tbody-tr"><td colspan="7">未查到该学生数据</td></tr>';
      } else {
        for (var i = 0; i < data.length; i++) {
          var flag = '';
          if (data[i].flags == null) {
            flag = '未备注';
          } else {
            flag = data[i].flags;
          }
          if (data[i].s_j_time === '' || data[i].s_j_time === null) {
            time = '暂无申请时间'
          } else {
            time = data[i].s_j_time
          }
          if (user_role == 2 || user_role == 4) {
            frxx_form_hmtl += '<tr class="r-tbody-tr">\n' +
              '                        <td>\n' +
              '                            <input type="checkbox" name="" stuid = "'+data[i].student.id+'" useid="'+data[i].student.user.id+'" sxid="' + data[i].id + '" >\n' +
              '                        </td>\n' +
              '                        <td>' + time + '</td>\n' +
              '                        <td>' + data[i].student.stuName + '</td>\n' +
              '                        <td>' + data[i].student.personalNum + '</td>\n' +
              '                        <td>' + data[i].student.parent + '</td>\n' +
              '                        <td>' + data[i].student.mobilephone + '</td>\n' +
              '                        <td>' + data[i].classinfo.subject.subject + '</td>\n' +
              '                        <td>' + data[i].classinfo.classname + '</td>\n' +
              '                        <td>' + data[i].classinfo.money + '</td>\n' +
              '                        <td>' + flag + '</td>\n' +
              '                        <td><span class="xg-bg btn-class-remark" title="' + flag + '" data-index="' + data[i].id + '" >备注</span></td>\n' +
              '                    </tr>';
          } else {
            frxx_form_hmtl += '<tr class="r-tbody-tr">\n' +
              '                        <td>\n' +
              '                            <input type="checkbox" name="" stuid = "'+data[i].student.id+'" useid="'+data[i].student.user.id+'" sxid="' + data[i].id + '" >\n' +
              '                        </td>\n' +
              '                        <td>' + time + '</td>\n' +
              '                        <td>' + data[i].student.stuName + '</td>\n' +
              '                        <td>' + data[i].student.personalNum + '</td>\n' +
              '                        <td>' + data[i].student.parent + '</td>\n' +
              '                        <td>' + data[i].student.mobilephone + '</td>\n' +
              '                        <td>' + data[i].classinfo.subject.subject + '</td>\n' +
              '                        <td>' + data[i].classinfo.classname + '</td>\n' +
              '                        <td>' + data[i].classinfo.money + '</td>\n' +
              '                        <td>' + flag + '</td>\n' +
              '                        <td><span class="xg-bgs" title="' + flag + '" data-index="' + data[i].id + '" >备注</span></td>\n' +
              '                    </tr>';
          }

        }
      }
      $('.r-cw-jfsh .r-cw-jfsh-new .r-table .r-tbody').html(frxx_form_hmtl);
    })
  })
  $('body').on('click', '.jf-btn-return', function () {
    $('.nav-cw-jfsh').click()
    $('.r-cw-jfsh-new').hide();
    $('.r-cw-jfsh-old').show();
  })
  $('body').on('click', '.r-cw-jfsh .r-table .r-tbody .r-tbody-tr td .btn-class-remark', function () {
    var bj_index = $(this).attr('data-index');
    var title = $(this).attr('title');
    var text_val = '';
    if (title == '未备注') {
      title = text_val;
    }
    layer.prompt({
      title: '为学生添加备注',
      value: title,
      formType: 2
    }, function (text, index) {
      $.post('/pjsys/updateFlag.action', {
        id: bj_index,
        flags: text
      }, function (data) {
        if (data == 1) {
          layer.msg('备注成功', {
            time: 1000
          }, function () {
            layer.close(index);
            if ($('.r-cw-jfsh .r-cw-jfsh-new').css('display') == 'block') {
              $('.r-cw-jfsh .r-cw-jfsh-new .r-wenzi .jf-btn-search').click()
            } else {
              $('.nav-cw-jfsh').click()
            }
          });
        } else {
          layer.msg('备注失败');
        }
      })
    });
  })
  // 选中事件
  $('body').on('click', '.r-cw-jfsh .r-table .r-thead .r-thead-tr th input[type="checkbox"]', function () {
    $('.r-cw-jfsh .r-table .r-tbody .r-tbody-tr td input[type="checkbox"]').prop("checked", $(this).prop("checked"));
  });
  $('body').on('click', '.r-cw-jfsh .r-table .r-tbody .r-tbody-tr td input[type="checkbox"]', function () {
    $(this).prop("checked", $(this).prop("checked"));
  });
  // 拒绝缴费
  $('.r-cw-jfsh .r-btn .jf-btn').click(function () {
    var checked = [];
    var userid = [];
    var stuid = [];
    for (var i = 0; i < $('.r-cw-jfsh .r-table .r-tbody .r-tbody-tr td input[type="checkbox"]').length; i++) {
      if ($('.r-cw-jfsh .r-table .r-tbody .r-tbody-tr td input[type="checkbox"]').eq(i).is(':checked')) {
        checked.push($('.r-cw-jfsh .r-table .r-tbody .r-tbody-tr td input[type="checkbox"]').eq(i).attr('sxid'))
        userid.push($('.r-cw-jfsh .r-table .r-tbody .r-tbody-tr td input[type="checkbox"]').eq(i).attr('useid'))
        stuid.push($('.r-cw-jfsh .r-table .r-tbody .r-tbody-tr td input[type="checkbox"]').eq(i).attr('stuid'))
      }
    }
    checked = checked.join(',');
    userid = userid.join(',');
    stuid = stuid.join(',');

    $.post('/pjsys/pay.action', {
      ids: checked,
      flag: 10,
      stu_ids: stuid,
      user_ids: userid,
      teacher_id: user_id
    }, function (data) {
      if (data === 1) {
        layer.msg('您已拒绝缴费成功', {
          time: 1000
        }, function () {
          if ($('.r-cw-jfsh .r-cw-jfsh-new').css('display') == 'block') {
            $('.r-cw-jfsh .r-cw-jfsh-new .r-wenzi .jf-btn-search').click()
          } else {
            $('.nav-cw-jfsh').click()
          }
        });
      } else {
        layer.msg('您拒绝缴费失败,请再尝试');
      }
    })
  })
  // 微信支付 pay: 1 flag: 3
  $('.r-cw-jfsh .r-btn .jf-btn-w').click(function () {
    jf_btn(1)
  })
  // 支付宝支付 pay: 2 flag: 3
  $('.r-cw-jfsh .r-btn .jf-btn-z').click(function () {
    jf_btn(2)
  })
  // 现金支付 pay : 3
  $('.r-cw-jfsh .r-btn .jf-btn-x').click(function () {
    jf_btn(3)
  })
  // pos支付 4
  $('.r-cw-jfsh .r-btn .jf-btn-p').click(function () {
    jf_btn(4)
  })
  // 缴费接口
  function jf_btn(pay) {
        var checked = [];
        var userid = [];
        var stuid = [];
        for (var i = 0; i < $('.r-cw-jfsh .r-table .r-tbody .r-tbody-tr td input[type="checkbox"]').length; i++) {
          if ($('.r-cw-jfsh .r-table .r-tbody .r-tbody-tr td input[type="checkbox"]').eq(i).is(':checked')) {
            checked.push($('.r-cw-jfsh .r-table .r-tbody .r-tbody-tr td input[type="checkbox"]').eq(i).attr('sxid'))
            userid.push($('.r-cw-jfsh .r-table .r-tbody .r-tbody-tr td input[type="checkbox"]').eq(i).attr('useid'))
            stuid.push($('.r-cw-jfsh .r-table .r-tbody .r-tbody-tr td input[type="checkbox"]').eq(i).attr('stuid'))
          }
        }
        checked = checked.join(',');
        userid = userid.join(',');
        stuid = stuid.join(',');
        $.post('/pjsys/pay.action', {
          ids: checked,
          flag: 3,
          stu_ids: stuid,
          user_ids: userid,
          teacher_id: user_id,
          pay:pay
        }, function (data) {
          if (data === 1) {
            layer.msg('您操作成功', {
              time: 1000
            }, function () {
              if ($('.r-cw-jfsh .r-cw-jfsh-new').css('display') == 'block') {
                $('.r-cw-jfsh .r-cw-jfsh-new .r-wenzi .jf-btn-search').click()
              } else {
                $('.nav-cw-jfsh').click()
              }
            });
          } else {
            layer.msg('操作失败,请再尝试');
          }
        })
  }
  //----------缴费审核结束----------


  //----------续费审核开始----------
  // -----续费审核
  $('.nav-cw-xfsh').on('click', function (e) {
    var first_page = 10; //得到每页显示的条数
    var first_total = 10; //总数据条数
    $.post('/pjsys/findAllInfo5.action', {
      page: 1
    }, function (data) {
      first_total = data.total;
      $('.r-cw-xfsh .r-cw-xfsh-old .r-table .r-tbody').html('');
      var frxx_form_hmtl = '';
      var time = '';
      var flag = '';
      for (var i = 0; i < data.datas.length; i++) {
        if (data.datas[i].flags == null) {
          flag = '未备注';
        } else {
          flag = data.datas[i].flags;
        }
        if (data.datas[i].s_x_time === '' || data.datas[i].s_x_time === null) {
          time = '暂无申请时间'
        } else {
          time = data.datas[i].s_x_time
        }
        if (user_role == 2 || user_role == 4) {
          frxx_form_hmtl += '<tr class="r-tbody-tr">\n' +
            '                        <td>\n' +
            '                            <input type="checkbox" name="" stuid = "'+data.datas[i].student.id+'" useid="'+data.datas[i].student.user.id+'" sxid="' + data.datas[i].id + '" >\n' +
            '                        </td>\n' +
            '                        <td>' + time + '</td>\n' +
            '                        <td>' + data.datas[i].student.stuName + '</td>\n' +
            '                        <td>' + data.datas[i].student.personalNum + '</td>\n' +
            '                        <td>' + data.datas[i].student.parent + '</td>\n' +
            '                        <td>' + data.datas[i].student.mobilephone + '</td>\n' +
            '                        <td>' + data.datas[i].classinfo.subject.subject + '</td>\n' +
            '                        <td>' + data.datas[i].classinfo.classname + '</td>\n' +
            '                        <td>' + data.datas[i].classinfo.money + '</td>\n' +
            '                        <td>' + flag + '</td>\n' +
            '                        <td><span class="xg-bg btn-class-remark"  title="' + flag + '" data-index="' + data.datas[i].id + '" >备注</span></td>\n' +
            '                    </tr>';
        } else {
          frxx_form_hmtl += '<tr class="r-tbody-tr">\n' +
            '                        <td>\n' +
            '                            <input type="checkbox" name="" stuid = "'+data.datas[i].student.id+'" useid="'+data.datas[i].student.user.id+'" sxid="' + data.datas[i].id + '" >\n' +
            '                        </td>\n' +
            '                        <td>' + time + '</td>\n' +
            '                        <td>' + data.datas[i].student.stuName + '</td>\n' +
            '                        <td>' + data.datas[i].student.personalNum + '</td>\n' +
            '                        <td>' + data.datas[i].student.parent + '</td>\n' +
            '                        <td>' + data.datas[i].student.mobilephone + '</td>\n' +
            '                        <td>' + data.datas[i].classinfo.subject.subject + '</td>\n' +
            '                        <td>' + data.datas[i].classinfo.classname + '</td>\n' +
            '                        <td>' + data.datas[i].classinfo.money + '</td>\n' +
            '                        <td>' + flag + '</td>\n' +
            '                        <td><span class="xg-bgs"  title="' + flag + '" data-index="' + data.datas[i].id + '" >备注</span></td>\n' +
            '                    </tr>';
        }

      }
      $('.r-cw-xfsh .r-cw-xfsh-old .r-table .r-tbody').html(frxx_form_hmtl);
      laypage.render({
        elem: 'renew'
          ,
        count: first_total
          ,
        limit: first_page,
        theme: "#65daf7",
        first: '首页',
        last: '尾页',
        jump: function (obj, first) {
          $.post('/pjsys/findAllInfo5.action', {
            page: obj.curr
          }, function (data) {
            bjlst_page = obj.curr;
            first_page = obj.first;
            var frxx_form_hmtl = '';
            var time = '';
            var flag = '';
            for (var i = 0; i < data.datas.length; i++) {
              if (data.datas[i].flags == null) {
                flag = '未备注';
              } else {
                flag = data.datas[i].flags;
              }
              if (data.datas[i].s_x_time === '' || data.datas[i].s_x_time === null) {
                time = '暂无申请时间'
              } else {
                time = data.datas[i].s_x_time
              }
              if (user_role == 2 || user_role == 4) {
                frxx_form_hmtl += '<tr class="r-tbody-tr">\n' +
                  '                        <td>\n' +
                  '                            <input type="checkbox" name="" stuid = "'+data.datas[i].student.id+'" useid="'+data.datas[i].student.user.id+'" sxid="' + data.datas[i].id + '"  >\n' +
                  '                        </td>\n' +
                  '                        <td>' + time + '</td>\n' +
                  '                        <td>' + data.datas[i].student.stuName + '</td>\n' +
                  '                        <td>' + data.datas[i].student.personalNum + '</td>\n' +
                  '                        <td>' + data.datas[i].student.parent + '</td>\n' +
                  '                        <td>' + data.datas[i].student.mobilephone + '</td>\n' +
                  '                        <td>' + data.datas[i].classinfo.subject.subject + '</td>\n' +
                  '                        <td>' + data.datas[i].classinfo.classname + '</td>\n' +
                  '                        <td>' + data.datas[i].classinfo.money + '</td>\n' +
                  '                        <td>' + flag + '</td>\n' +
                  '                        <td><span class="xg-bg btn-class-remark"  title="' + flag + '" data-index="' + data.datas[i].id + '" >备注</span></td>\n' +
                  '                    </tr>';
              } else {
                frxx_form_hmtl += '<tr class="r-tbody-tr">\n' +
                  '                        <td>\n' +
                  '                            <input type="checkbox" name="" stuid = "'+data.datas[i].student.id+'" useid="'+data.datas[i].student.user.id+'" sxid="' + data.datas[i].id + '"  >\n' +
                  '                        </td>\n' +
                  '                        <td>' + time + '</td>\n' +
                  '                        <td>' + data.datas[i].student.stuName + '</td>\n' +
                  '                        <td>' + data.datas[i].student.personalNum + '</td>\n' +
                  '                        <td>' + data.datas[i].student.parent + '</td>\n' +
                  '                        <td>' + data.datas[i].student.mobilephone + '</td>\n' +
                  '                        <td>' + data.datas[i].classinfo.subject.subject + '</td>\n' +
                  '                        <td>' + data.datas[i].classinfo.classname + '</td>\n' +
                  '                        <td>' + data.datas[i].classinfo.money + '</td>\n' +
                  '                        <td>' + flag + '</td>\n' +
                  '                        <td><span class="xg-bgs"  title="' + flag + '" data-index="' + data.datas[i].id + '" >备注</span></td>\n' +
                  '                    </tr>';
              }

            }
            $('.r-cw-xfsh .r-cw-xfsh-old .r-table .r-tbody').html(frxx_form_hmtl);
          })
        }
      })
    })
    $('.r-cw-xfsh').show().siblings().hide();
    e.stopPropagation()
  })
  //续费审核
  $('body').on('click', '.xf-search-btn', function () {
    $('.r-cw-xfsh-old').hide();
    $('.r-cw-xfsh-new').show();
  })
  $('body').on('click', '.xf-btn-return', function () {
    $('.nav-cw-xfsh').click()
    $('.r-cw-xfsh-new').hide();
    $('.r-cw-xfsh-old').show();
  })
  $('.r-cw-xfsh .r-cw-xfsh-new .r-wenzi .xf-btn-search').click(function () {
    var stuName = $('#xf-find-name').val()
    if (stuName == '') {
      layer.msg('请输入学生名字');
      return false;
    }
    $.post('/pjsys/show2.action', {
      stuName: stuName
    }, function (data) {
      var frxx_form_hmtl = '';
      var flag = '';
      if (data.length == 0) {
        frxx_form_hmtl += '<tr class="r-tbody-tr"><td colspan="7">未查到该学生数据</td></tr>';
      } else {
        for (var i = 0; i < data.length; i++) {
          var flag = '';
          if (data[i].flags == null) {
            flag = '未备注';
          } else {
            flag = data[i].flags;
          }
          if (data[i].s_x_time === '' || data[i].s_x_time === null) {
            time = '暂无申请时间'
          } else {
            time = data[i].s_x_time
          }
          if (user_role == 2 || user_role == 4) {
            frxx_form_hmtl += '<tr class="r-tbody-tr">\n' +
              '                        <td>\n' +
              '                            <input type="checkbox" stuid = "'+data[i].student.id+'" useid="'+data[i].student.user.id+'" name="" sxid="' + data[i].id + '" >\n' +
              '                        </td>\n' +
              '                        <td>' + time + '</td>\n' +
              '                        <td>' + data[i].student.stuName + '</td>\n' +
              '                        <td>' + data[i].student.personalNum + '</td>\n' +
              '                        <td>' + data[i].student.parent + '</td>\n' +
              '                        <td>' + data[i].student.mobilephone + '</td>\n' +
              '                        <td>' + data[i].classinfo.subject.subject + '</td>\n' +
              '                        <td>' + data[i].classinfo.classname + '</td>\n' +
              '                        <td>' + data[i].classinfo.money + '</td>\n' +
              '                        <td>' + flag + '</td>\n' +
              '                        <td><span class="xg-bg btn-class-remark" title="' + flag + '" data-index="' + data[i].id + '" >备注</span></td>\n' +
              '                    </tr>';
          } else {
            frxx_form_hmtl += '<tr class="r-tbody-tr">\n' +
              '                        <td>\n' +
              '                            <input type="checkbox" name="" stuid = "'+data[i].student.id+'" useid="'+data[i].student.user.id+'" sxid="' + data[i].id + '" >\n' +
              '                        </td>\n' +
              '                        <td>' + time + '</td>\n' +
              '                        <td>' + data[i].student.stuName + '</td>\n' +
              '                        <td>' + data[i].student.personalNum + '</td>\n' +
              '                        <td>' + data[i].student.parent + '</td>\n' +
              '                        <td>' + data[i].student.mobilephone + '</td>\n' +
              '                        <td>' + data[i].classinfo.subject.subject + '</td>\n' +
              '                        <td>' + data[i].classinfo.classname + '</td>\n' +
              '                        <td>' + data[i].classinfo.money + '</td>\n' +
              '                        <td>' + flag + '</td>\n' +
              '                        <td><span class="xg-bgs" title="' + flag + '" data-index="' + data[i].id + '" >备注</span></td>\n' +
              '                    </tr>';
          }

        }
      }
      $('.r-cw-xfsh .r-cw-xfsh-new .r-table .r-tbody').html(frxx_form_hmtl);
    })
  })
  // 选中事件
  $('body').on('click', '.r-cw-xfsh .r-table .r-thead .r-thead-tr th input[type="checkbox"]', function () {
    $('.r-cw-xfsh .r-table .r-tbody .r-tbody-tr td input[type="checkbox"]').prop("checked", $(this).prop("checked"));
  });
  $('body').on('click', '.r-cw-xfsh .r-table .r-tbody .r-tbody-tr td input[type="checkbox"]', function () {
    $(this).prop("checked", $(this).prop("checked"));
  });
  // 拒绝续费
  $('.r-cw-xfsh .r-btn .xf-btn').click(function () {
    var checked = [];
    var userid = [];
    var stuid = [];
    for (var i = 0; i < $('.r-cw-xfsh .r-table .r-tbody .r-tbody-tr td input[type="checkbox"]').length; i++) {
      if ($('.r-cw-xfsh .r-table .r-tbody .r-tbody-tr td input[type="checkbox"]').eq(i).is(':checked')) {
        checked.push($('.r-cw-xfsh .r-table .r-tbody .r-tbody-tr td input[type="checkbox"]').eq(i).attr('sxid'))
        userid.push($('.r-cw-xfsh .r-table .r-tbody .r-tbody-tr td input[type="checkbox"]').eq(i).attr('useid'))
        stuid.push($('.r-cw-xfsh .r-table .r-tbody .r-tbody-tr td input[type="checkbox"]').eq(i).attr('stuid'))
      }
    }
    checked = checked.join(',');
    userid = userid.join(',');
    stuid = stuid.join(',');
    $.post('/pjsys/pay.action', {
      ids: checked,
      flag: 11,
      stu_ids: stuid,
      user_ids: userid,
      teacher_id: user_id
    }, function (data) {
      if (data === 1) {
        layer.msg('您已拒绝续费成功', {
          time: 1000
        }, function () {
          if ($('.r-cw-xfsh .r-cw-xfsh-new').css('display') == 'block') {
            $('.r-cw-xfsh .r-cw-xfsh-new .r-wenzi .xf-btn-search').click()
          } else {
            $('.nav-cw-xfsh').click()
          }
        });
      } else {
        layer.msg('您拒绝续费失败,请再尝试');
      }
    })
  })
  // 支付方式
  // 微信支付 pay: 1 flag: 3
  $('.r-cw-xfsh .r-btn .xf-btn-w').click(function () {
    xf_btn(1)
  })
  // 支付宝支付 pay: 2 flag: 3
  $('.r-cw-xfsh .r-btn .xf-btn-z').click(function () {
    xf_btn(2)
  })
  // 现金支付 pay : 3
  $('.r-cw-xfsh .r-btn .xf-btn-x').click(function () {
    xf_btn(3)
  })
  // pos支付 4
  $('.r-cw-xfsh .r-btn .xf-btn-p').click(function () {
    xf_btn(4)
  })
  // 续费方式封装
  function xf_btn(pay) {
    var checked = [];
    var userid = [];
    var stuid = [];
    for (var i = 0; i < $('.r-cw-xfsh .r-table .r-tbody .r-tbody-tr td input[type="checkbox"]').length; i++) {
      if ($('.r-cw-xfsh .r-table .r-tbody .r-tbody-tr td input[type="checkbox"]').eq(i).is(':checked')) {
        checked.push($('.r-cw-xfsh .r-table .r-tbody .r-tbody-tr td input[type="checkbox"]').eq(i).attr('sxid'))
        userid.push($('.r-cw-xfsh .r-table .r-tbody .r-tbody-tr td input[type="checkbox"]').eq(i).attr('useid'))
        stuid.push($('.r-cw-xfsh .r-table .r-tbody .r-tbody-tr td input[type="checkbox"]').eq(i).attr('stuid'))
      }
    }
    checked = checked.join(',');
    userid = userid.join(',');
    stuid = stuid.join(',');
    $.post('/pjsys/pay.action', {
      ids: checked,
      flag: 9,
      stu_ids: stuid,
      user_ids: userid,
      teacher_id: user_id,
      pay: pay
    }, function (data) {
      if (data === 1) {
        layer.msg('操作成功', {
          time: 1000
        }, function () {
          if ($('.r-cw-xfsh .r-cw-xfsh-new').css('display') == 'block') {
            $('.r-cw-xfsh .r-cw-xfsh-new .r-wenzi .xf-btn-search').click()
          } else {
            $('.nav-cw-xfsh').click()
          }
        });
      } else {
        layer.msg('操作失败,请再尝试');
      }
    })
  }
  $('body').on('click', '.r-cw-xfsh .r-table .r-tbody .r-tbody-tr td .btn-class-remark', function () {
    var bj_index = $(this).attr('data-index');
    var title = $(this).attr('title');
    var text_val = '';
    if (title == '未备注') {
      title = text_val;
    }
    layer.prompt({
      title: '为学生添加备注',
      value: title,
      formType: 2
    }, function (text, index) {
      $.post('/pjsys/updateFlag.action', {
        id: bj_index,
        flags: text
      }, function (data) {
        if (data == 1) {
          layer.msg('备注成功', {
            time: 1000
          }, function () {
            layer.close(index);
            if ($('.r-cw-xfsh r-cw-xfsh-new').css('display') == 'block') {
              $('.r-cw-xfsh .r-cw-xfsh-new .r-wenzi .xf-btn-search').click()
            } else {
              $('.nav-cw-xfsh').click()
            }
          });
        } else {
          layer.msg('备注失败');
        }
      })
    });
  })

  //----------续费审核结束----------
  //----------退费审核开始----------
  // -----退费审核
  $('.nav-cw-tfsh').on('click', function (e) {
    var first_page = 10; //得到每页显示的条数
    var first_total = 10; //总数据条数
    $.post('/pjsys/findAllInfo6.action', {
      page: 1
    }, function (data) {
      first_total = data.total;
      var time = ''
      $('.r-cw-tfsh .r-cw-tfsh-old .r-table .r-tbody').html('');
      var frxx_form_hmtl = '';
      for (var i = 0; i < data.datas.length; i++) {
        if (data.datas[i].s_t_time === '' || data.datas[i].s_t_time === null) {
          time = '暂无申请时间'
        } else {
          time = data.datas[i].s_t_time
        }
      }
      for (var i = 0; i < data.datas.length; i++) {
        frxx_form_hmtl += '<tr class="r-tbody-tr">\n' +
          '                        <td>\n' +
          '                            <input type="checkbox" stuid = "'+data.datas[i].student.id+'" useid="'+data.datas[i].student.user.id+'" name="" sxid="' + data.datas[i].id + '"   >\n' +
          '                        </td>\n' +
          '                        <td>' + time + '</td>\n' +
          '                        <td>' + data.datas[i].student.stuName + '</td>\n' +
          '                        <td>' + data.datas[i].student.personalNum + '</td>\n' +
          '                        <td>' + data.datas[i].student.parent + '</td>\n' +
          '                        <td>' + data.datas[i].student.mobilephone + '</td>\n' +
          '                        <td>' + data.datas[i].classinfo.subject.subject + '</td>\n' +
          '                        <td>' + data.datas[i].classinfo.classname + '</td>\n' +
          '                        <td>' + data.datas[i].classinfo.money + '</td>\n' +
          '                    </tr>';
      }
      $('.r-cw-tfsh .r-cw-tfsh-old .r-table .r-tbody').html(frxx_form_hmtl);
      laypage.render({
        elem: 'refund'
          ,
        count: first_total
          ,
        limit: first_page,
        theme: "#65daf7",
        first: '首页',
        last: '尾页',
        jump: function (obj, first) {
          $.post('/pjsys/findAllInfo6.action', {
            page: obj.curr
          }, function (data) {
            bjlst_page = obj.curr;
            first_page = obj.first;
            var frxx_form_hmtl = '';
            var time = '';
            for (var i = 0; i < data.datas.length; i++) {
              if (data.datas[i].s_t_time === '' || data.datas[i].s_t_time === null) {
                time = '暂无申请时间'
              } else {
                time = data.datas[i].s_t_time
              }
            }
            for (var i = 0; i < data.datas.length; i++) {
              frxx_form_hmtl += '<tr class="r-tbody-tr">\n' +
                '                        <td>\n' +
                '                            <input type="checkbox" name="" stuid = "'+data.datas[i].student.id+'" useid="'+data.datas[i].student.user.id+'" sxid="' + data.datas[i].id + '"   >\n' +
                '                        </td>\n' +
                '                        <td>' + time + '</td>\n' +
                '                        <td>' + data.datas[i].student.stuName + '</td>\n' +
                '                        <td>' + data.datas[i].student.personalNum + '</td>\n' +
                '                        <td>' + data.datas[i].student.parent + '</td>\n' +
                '                        <td>' + data.datas[i].student.mobilephone + '</td>\n' +
                '                        <td>' + data.datas[i].classinfo.subject.subject + '</td>\n' +
                '                        <td>' + data.datas[i].classinfo.classname + '</td>\n' +
                '                        <td>' + data.datas[i].classinfo.money + '</td>\n' +
                '                    </tr>';
            }
            $('.r-cw-tfsh .r-cw-tfsh-old .r-table .r-tbody').html(frxx_form_hmtl);
          })
        }
      })
    })
    $('.r-cw-tfsh').show().siblings().hide();
    e.stopPropagation()
  })
  //退费按照姓名查找
  $('body').on('click', '.tf-search-btn', function () {
    $('.r-cw-tfsh-old').hide();
    $('.r-cw-tfsh-new').show();
  })
  $('body').on('click', '.tf-btn-return', function () {
    $('.nav-cw-tfsh').click();
    $('.r-cw-tfsh-new').hide();
    $('.r-cw-tfsh-old').show();
  })
  // 选中事件
  $('body').on('click', '.r-cw-tfsh .r-table .r-thead .r-thead-tr th input[type="checkbox"]', function () {
    $('.r-cw-tfsh .r-table .r-tbody .r-tbody-tr td input[type="checkbox"]').prop("checked", $(this).prop("checked"));
  });
  $('body').on('click', '.r-cw-tfsh .r-table .r-tbody .r-tbody-tr td input[type="checkbox"]', function () {
    $(this).prop("checked", $(this).prop("checked"));
  });
  // 确认退费
  $('.r-cw-tfsh .r-btn .tf-btn').click(function () {
    var checked = [];
    for (var i = 0; i < $('.r-cw-tfsh .r-table .r-tbody .r-tbody-tr td input[type="checkbox"]').length; i++) {
      if ($('.r-cw-tfsh .r-table .r-tbody .r-tbody-tr td input[type="checkbox"]').eq(i).is(':checked')) {
        checked.push($('.r-cw-tfsh .r-table .r-tbody .r-tbody-tr td input[type="checkbox"]').eq(i).attr('sxid'))
      }
    }
    checked = checked.join(',');
    $.post('/pjsys/pay.action', {
      ids: checked,
      flag: 12 //退费为6
    }, function (data) {
      if (data === 1) {
        layer.msg('您已拒绝退费成功', {
          time: 1000
        }, function () {
          if ($('.r-cw-tfsh .r-cw-tfsh-new').css('display') == 'block') {
            $('.r-cw-tfsh .r-cw-tfsh-new .r-wenzi .tf-btn-search').click()
          } else {
            $('.nav-cw-tfsh').click()
          }
        });
      } else {
        layer.msg('您拒绝退费失败,请再尝试拒绝退费');
      }
    })
  })
    // 微信支付 pay: 1 flag: 3
    $('.r-cw-tfsh .r-btn .tf-btn-w').click(function () {
      tf_btn(1)
    })
    // 支付宝支付 pay: 2 flag: 3
    $('.r-cw-tfsh .r-btn .tf-btn-z').click(function () {
      tf_btn(2)
    })
    // 现金支付 pay : 3
    $('.r-cw-tfsh .r-btn .tf-btn-x').click(function () {
      tf_btn(3)
    })
    // pos支付 4
    $('.r-cw-tfsh .r-btn .tf-btn-p').click(function () {
      tf_btn(4)
    })
  function tf_btn (pay) {
  var checked = [];
  for (var i = 0; i < $('.r-cw-tfsh .r-table .r-tbody .r-tbody-tr td input[type="checkbox"]').length; i++) {
    if ($('.r-cw-tfsh .r-table .r-tbody .r-tbody-tr td input[type="checkbox"]').eq(i).is(':checked')) {
      checked.push($('.r-cw-tfsh .r-table .r-tbody .r-tbody-tr td input[type="checkbox"]').eq(i).attr('sxid'))
    }
  }
  checked = checked.join(',');
  $.post('/pjsys/pay.action', {
    ids: checked,
    flag: 6, //退费为6
    pay: pay
  }, function (data) {
    if (data === 1) {
      layer.msg('操作成功', {
        time: 1000
      }, function () {
        if ($('.r-cw-tfsh .r-cw-tfsh-new').css('display') == 'block') {
          $('.r-cw-tfsh .r-cw-tfsh-new .r-wenzi .tf-btn-search').click()
        } else {
          $('.nav-cw-tfsh').click()
        }
      });
    } else {
      layer.msg('操作失败,请再尝试');
    }
  })
  }
  $('.r-cw-tfsh .r-cw-tfsh-new .r-wenzi .tf-btn-search').click(function () {
    var stuName = $('#tf-find-name').val()
    if (stuName == '') {
      layer.msg('请输入学生名字');
      return false;
    }
    $.post('/pjsys/show3.action', {
      stuName: stuName
    }, function (data) {
      var frxx_form_hmtl = '';
      var flag = '';
      var time = '';
      if (data.length == 0) {
        frxx_form_hmtl += '<tr class="r-tbody-tr"><td colspan="5">未查到该学生数据</td></tr>';
      } else {
        for (var i = 0; i < data.length; i++) {
          if (data[i].flags == null) {
            flag = '未备注';
          } else {
            flag = data[i].flags;
          }
          if (data[i].s_t_time === '' || data[i].s_t_time === null) {
            time = '暂无申请时间'
          } else {
            time = data[i].s_t_time
          }
          frxx_form_hmtl += '<tr class="r-tbody-tr">\n' +
            '                        <td>\n' +
            '                            <input type="checkbox" stuid = "'+data[i].student.id+'" useid="'+data[i].student.user.id+'" name="" sxid="' + data[i].id + '"   >\n' +
            '                        </td>\n' +
            '                        <td>' + time + '</td>\n' +
            '                        <td>' + data[i].student.stuName + '</td>\n' +
            '                        <td>' + data[i].student.personalNum + '</td>\n' +
            '                        <td>' + data[i].student.parent + '</td>\n' +
            '                        <td>' + data[i].student.mobilephone + '</td>\n' +
            '                        <td>' + data[i].classinfo.subject.subject + '</td>\n' +
            '                        <td>' + data[i].classinfo.classname + '</td>\n' +
            '                        <td>' + data[i].classinfo.money + '</td>\n' +
            '                    </tr>';
        }
      }
      $('.r-cw-tfsh .r-cw-tfsh-new .r-table .r-tbody').html(frxx_form_hmtl);
    })
  })

  //----------退费审核结束----------
  //----------汇总开始----------
  // -----数据汇总
  $('.nav-cw-sjhz').on('click', function (e) {
    $.post('/pjsys/findAllMoney.action', function (data) {
      $('.r-cw-sjhz .layui-tab .layui-tab-content .layui-tab-item:eq(0) .r-table .r-tbody .r-tbody-tr td').eq(0).text(data)
    })
    $.post('/pjsys/findAllBackmoney.action', function (data) {
      $('.r-cw-sjhz .layui-tab .layui-tab-content .layui-tab-item:eq(0) .r-table .r-tbody .r-tbody-tr td').eq(1).text(data)
    })
    $('.r-cw-sjhz').show().siblings().hide();
    e.stopPropagation()
  })
  // 初始化时间
  laydate.render({
    elem: '#summary' //指定元素
  });
  $('body').on('click','.search-summary',function() {
    console.log($("#summary").val())
    var time = $("#summary").val()
    if (time === '') {
      layer.msg('请选择日期')
    } else{
    $.post('/pjsys/findAllMoney.action', { dateTime: time},function (data) {
      $('.r-cw-sjhz .layui-tab .layui-tab-content .layui-tab-item:eq(0) .r-table .r-tbody .r-tbody-tr td').eq(0).text(data)
    })
    $.post('/pjsys/findAllBackmoney.action', { dateTime: time},function (data) {
      $('.r-cw-sjhz .layui-tab .layui-tab-content .layui-tab-item:eq(0) .r-table .r-tbody .r-tbody-tr td').eq(1).text(data)
    })
    }
  })
  // 费用明细 money()
  /**money()
   * id: 分页id  flag: 状态值 page:页码 htmls: 显示位置
   */
  function money (id,flag,elem,htmls) {
      $.post('/pjsys/findMoneyByClass.action',{classinfo_id: id,page:1,flag:flag},function(data){
        laypage.render({
          elem:elem
          ,count:data.total
          , theme: '#65daf7'
          ,first: '首页'
          ,last: '尾页'
          ,jump:function(obj){
            $.post("/pjsys/findMoneyByClass.action", { classinfo_id: id, page: obj.curr, flag: flag},function(data){
              var html = '';
              var s_time = '';
              var sh_time = '';
              var pay = '';
              if(data.total==0){
                html += '<tr class="r-tbody-tr">\\n\' +\n' +
                '     <td colspan="7">暂无数据</td>' +
                ' </tr>';
              }else{
                for (var i = 0; i < data.datas.length; i++) {
                  if (data.datas[i][6] === 1) {
                    pay = '微信'
                  } else if (data.datas[i][6] === 2) {
                    pay = '支付宝'
                  } else if (data.datas[i][6] === 3) {
                    pay = '现金'
                  } else if (data.datas[i][6] === 4) {
                    pay = 'POS机'
                  } else{
                    pay = '暂无支付方式'
                  }
                  data.datas[i][4] === null ? s_time = '暂无申请时间' : s_time = data.datas[i][4]
                  data.datas[i][5] === null ? sh_time = '暂无审核时间': sh_time = data.datas[i][5]
                  html += '<tr class="r-tbody-tr">\\n\' +\n' +
                    '          <td>' + data.datas[i][0] + '</td>' +
                    '          <td>' + data.datas[i][1] + '</td>' +
                    '          <td>' + data.datas[i][2] + '</td>' +
                    '          <td>' + s_time + '</td>' +
                    '          <td>' + sh_time + '</td>' +
                    '          <td>' + pay + '</td>' +
                    '          <td>' + data.datas[i][3] + '</td>' +
                    '          </tr>';
                }
              }
              $(htmls).html(html);
            });
          }
        })
      })
    }
  //监听事件
  element.on('tab(dataBox)', function (data) {
    if ($(this).index() == 1) {
      findMoneyRenCount(3, '.cost-stu-num1 span')
      navkq('.jiaofei-div select[name="jiaofei"]')
        form.on('select(jftabbtn)', function (data) {
          money(data.value, 3, 'payment-details', '.r-cw-sjhz .layui-tab .layui-tab-content .layui-tab-item:eq(1) .r-table .r-tbody')
        });
    } else if ($(this).index() == 2) {
      findMoneyRenCount(9, '.cost-stu-num2 span')
      navkq('body .r-cw-sjhz .layui-tab-content .layui-tab-item:eq(2) select[name="xufei"]')
      form.on("select(xftabbtn)", function(data) {
        money(data.value, 9, "xftabbtn-details", ".r-cw-sjhz .layui-tab .layui-tab-content .layui-tab-item:eq(2) .r-table .r-tbody");
      });
    } else if ($(this).index() == 3) {
      findMoneyRenCount(6, '.cost-stu-num3 span')
      navkq('body .r-cw-sjhz .layui-tab-content .layui-tab-item:eq(3) select[name="tuifei"]')
      form.on("select(tftabbtn)", function(data) {
        money(data.value, 6, "tftabbtn-details", ".r-cw-sjhz .layui-tab .layui-tab-content .layui-tab-item:eq(3) .r-table .r-tbody");
      });
    }
    else if ($(this).index() == 4) {
      let first_page = 10; //得到每页显示的条数
      let first_total = 10; //总数据条数
      $.post('/pjsys/find1.action', {
        page: 1
      }, function (data) {
        first_total = data.total;
        laypage.render({
          elem: 'payment-details1'
            ,
          count: first_total
            ,
          limit: first_page,
          theme: "#65daf7",
          first: '首页',
          last: '尾页',
          jump: function (obj, first) {
            $.post('/pjsys/find1.action', {
              page: obj.curr
            }, function (data) {
              bjlst_page = obj.curr;
              first_page = obj.first;
              $('.r-cw-sjhz .layui-tab .layui-tab-content .layui-tab-item:eq(2) .r-table .r-tbody').html('');
              var frxx_form_hmtl = '';
              for (var i = 0; i < data.datas.length; i++) {
                if (data.datas[i][1] == null) {
                  flag1 = '0'
                } else {
                  flag1 = data.datas[i][1]
                }
                if (data.datas[i][2] == null) {
                  flag2 = '0'
                } else {
                  flag2 = data.datas[i][2]
                }
                if (data.datas[i][3] == null) {
                  flag3 = '0'
                } else {
                  flag3 = data.datas[i][3]
                }
                if (data.datas[i][4] == null) {
                  flag4 = '0'
                } else {
                  flag4 = data.datas[i][4]
                }
                frxx_form_hmtl += '<tr class="r-tbody-tr">\\n\' +\n' +
                  '                                                         <td>' + data.datas[i][0] + '</td>' +
                  '                                                        <td>' + flag2 + '</td>' +
                  '                                                        <td>' + flag1 + '</td>' +
                  '                                                        <td>' + flag4 + '</td>' +
                  '                                                        <td>' + flag3 + '</td>' +
                  '                                                     </tr>';
              }
              $('.r-cw-sjhz .layui-tab .layui-tab-content .layui-tab-item:eq(4) .r-table .r-tbody').html(frxx_form_hmtl);
            })
          }
        })
      })
    }
  });
  function findMoneyRenCount ( flag , dom) {
    $.post('/pjsys/findMoneyRenCount.action',{flag: flag},function(data) {
      console.log(data);
      $(dom).text(data)
    })
  }
  // 按姓名查找添加
  $("body").on("click", ".subject-search-btn",function(){
    console.log($("#subject-search").val());
    var sub = ''
    sub = $("#subject-search").val()
    if(sub === '') {
      layer.msg('请输入科目名称')
    }else{
      $.post("/pjsys/find1.action", { subject: sub }, function (data) {
        if(data.total === 0) {
          layer.msg('搜索不到该科目')
        } else {
          $("#subject-search").val('')
          var frxx_form_hmtl = '';
          for (var i = 0; i < data.datas.length; i++) {
            if (data.datas[i][1] == null) {
              flag1 = '0'
            } else {
              flag1 = data.datas[i][1]
            }
            if (data.datas[i][2] == null) {
              flag2 = '0'
            } else {
              flag2 = data.datas[i][2]
            }
            if (data.datas[i][3] == null) {
              flag3 = '0'
            } else {
              flag3 = data.datas[i][3]
            }
            if (data.datas[i][4] == null) {
              flag4 = '0'
            } else {
              flag4 = data.datas[i][4]
            }
            // frxx_form_hmtl += '<tr class="r-tbody-tr">\\n\' +\n' +
            //   ' <td>' + data.datas[i][0] + '</td>' +
            //   '<td>' + flag2 + '</td>' +
            //   '<td>' + flag1 + '</td>' +
            //   '<td>' + flag4 + '</td>' +
            //   '<td>' + flag3 + '</td>' +
            //   '</tr>';
            frxx_form_hmtl += '<div style="margin-left:50px;margin-top:20px" class="layui-form-item"><label for="subject-search">缴费人数:</label><input class="input" type="text" value="' + flag2 + '"></div>' + '<div style="margin-left:50px;" class="layui-form-item"><label for="subject-search">收入金额:</label><input class="input" type="text" value="' + flag1 + '"></div>' + '<div style="margin-left:50px;" class="layui-form-item"><label for="subject-search">退款人数:</label><input class="input" type="text" value="' + flag4 + '"></div>' + '<div style="margin-left:50px;" class="layui-form-item"><label for="subject-search">退款金额:</label><input class="input" type="text" value="' + flag3 + '"></div>';
          }
          layer.open({
            title: sub,
            type: 1,
            // skin: "layui-layer-rim", //加上边框
            // skin: "layui-layer-nobg",
            area: ["420px", "300px"], //宽高
            content: frxx_form_hmtl
          });
        }
      });
    }
  });
  $(".jiaofei-div .add-student").click(function(){
    $(".fade-jf").show();
    navkq(".fade-jf .class-name");
  });
  // 姓名查询
  $(".fade-search-stuName").click(function(){
    var stuName = ''
    stuName = $(".student-name").val();
    if (stuName == ''){
      layer.msg('请输入正确姓名')
    }else{
      $.post("/pjsys/queryStuNameNotInfo", { stuName: stuName }, function (data) {
        var html = '';
        if(data == ''){
          html = '<tr class="r-tbody-tr"><td colspan="3">没有查到该学生</td></tr>';
        }
        for(var i =0 ;i<data.length;i++){
          html += '<tr class="r-tbody-tr"><td><input type="radio" name="radio-stuName" value="'+data[i].id+'"></td><td>' + data[i].stuName + '</td><td>' + data[i].personalNum+'</td></tr>';
        }
        $(".fade-jf .r-table .r-tbody").html(html);
      });
    }
  });
  // 
  $(".fade-jf .fade-btn-add-student").click(function() {
    var id = "";
    var class_id = "";
    id = $('input[name="radio-stuName"]:checked').val();
    class_id = $(".fade-jf .class-name").val();
    if (id == '' || class_id == ''){
      layer.msg('请先选择学生和班级',{time:2000})
    }else{
      $.post("/pjsys/addInfoInClass_idStu_idFlag", {
        my_flag: 3,
        my_classinfo_id: class_id,
        my_stu_id: id
      }, function (data) {
        if(data == 1){
          layer.msg('添加成功')
          $(".fade-jf .class-name").value('');
          $(".fade-jf").hide();
        }else{
          layer.msg('添加失败')
        }
      }); 
    }
  });
  $(".fade-jf .fade-btn-add-student-del").click(function(){
    $(".fade-jf").hide();
  });
  // 发票录入
  $('.r-fpbm').click(function () {
    $('.fade-lrfp').show();
  })
  $('.r-lrfp-lr').click(function () {
    $('.fade-lrfp').hide();
  })
  // 取消
  $('.r-lrfp-del').click(function () {
      $('.fade-lrfp').hide();
    })
  //----------汇总结束----------

  //----------发票设置开始----------
  // ----发票设置
  $('.nav-cw-fpsz').on('click', function (e) {
    $('.r-cw-fpsz').show().siblings().hide();
    e.stopPropagation()
  })

  //----------发票设置结束----------


  //================财务端结束==========
  //================管理员开始==========
  // -----账户管理
  $('.nav-admin-zhgl').on('click', function (e) {
    // =========显示列表
    $.post('/pjsys/findAllTeacher.action', function (data) {
      var html = ''
      for (var i = 0; i < data.length; i++) {
        var role = ''
        if (data[i].role == 1) {
          role = '教务'
        } else if (data[i].role == 2) {
          role = '财务'
        } else if (data[i].role == 3) {
          role = '班主任'
        } else if (data[i].role == 4) {
          role = '管理员'
        }
        if (user_role == 4) {
          html += '<tr class="r-tbody-tr"><td>' + data[i].username + '</td><td>' + data[i].password + '</td><td>' + role + '</td><td><span class="xg-bg admin-btn-xg" data_id="' + data[i].id + '">修改</span><span class="del-bg admin-btn-del" data_id="' + data[i].id + '">删除</span></td></tr>'
        } else {
          html += '<tr class="r-tbody-tr"><td>' + data[i].username + '</td><td>' + data[i].password + '</td><td>' + role + '</td><td><span class="xg-bgs" data_id="' + data[i].id + '">修改</span><span class="xg-bgs" data_id="' + data[i].id + '">删除</span></td></tr>'
        }
      }
      $('.r-admin .r-table .r-tbody-admin').html(html)
    })
    $('.r-admin').show().siblings().hide();
    e.stopPropagation()
  })



  // =========新增账号
  $('.r-zhgl-xz').click(function () {
    $('.fade-xzzh').show();
  })
  // 取消新增账号
  $('.btn-xz-account-number-del').on('click', function () {
    $('.fade-xzzh').hide();
  })
  // =========确定新增账号开始
  $('.btn-xz-account-number').on('click', function () {
    var role = $('#xz-role').val();
    var username = $('#xz-username').val()
    var password = $('#xz-passworld').val()
    if (role == '') {
      layer.msg('请选择角色');
      return false;
    }
    if (username == '') {
      layer.msg('请输入用户名');
      return false;
    }
    if (password == '') {
      layer.msg('请输入密码');
      return false;
    }
    $.post('/pjsys/addTeacher.action', {
      role: role,
      username: username,
      password: password
    }, function (data) {
      if (data === 1) {
        layer.msg('新增账户成功', {
          time: 2000
        }, function () {
          $('.nav-admin-zhgl').click()
          $('.fade-xzzh').hide();
        })
      } else {
        layer.msg('新增账户失败')
      }
    })
    $('.fade-xzzh').hide();
  })
  // =========确定新增账号结束
  //========修改账号
  $('body').on('click', '.r-tbody-admin .admin-btn-xg', function () {
    var id = $(this).attr('data_id');
    $('#xg-username').val($(this).parent('td').siblings('td').eq(0).text());
    $('#xg-password').val($(this).parent('td').siblings('td').eq(1).text());
    //  $('.r-admin .r-tbody-admin .xg-bg').attr('adminxg_id',$(this).attr('adminxg_id'))
    $('.btn-xg-account-number').attr('data_id', id)
    var role = $(this).parent('td').siblings('td').eq(2).text();
    if (role === '教务') {
      var html = '<option value="1" selected="selected">教务</option><option value="2">财务</option><option value="3">班主任</option><option value="4">管理员</option>'
      $('#xg-role').html(html)
    } else if (role === '财务') {
      var html = '<option value="1" >教务</option><option value="2" selected="selected">财务</option><option value="3">班主任</option><option value="4">管理员</option>'
      $('#xg-role').html(html)
    } else if (role === '班主任') {
      var html = '<option value="1" >教务</option><option value="2" >财务</option><option value="3" selected="selected">班主任</option><option value="4">管理员</option>'
      $('#xg-role').html(html)
    } else if (role === '管理员') {
      var html = '<option value="1" >教务</option><option value="2" >财务</option><option value="3" >班主任</option><option value="4" selected="selected">管理员</option>'
      $('#xg-role').html(html)
    }
    // 下拉框变化
    form.render()
    $('.fade-xgzh').show();
  })
  // 确定修改账号
  $('.btn-xg-account-number').on('click', function () {
    var id = $(this).attr('data_id');
    var role = $('#xg-role').val();
    var username = $('#xg-username').val()
    var password = $('#xg-password').val()
    form.render()
    $.post('/pjsys/updateTeacher.action', {
      id: id,
      username: username,
      password: password,
      role: role
    }, function (data) {
      if (data === 1) {
        layer.msg('修改成功', {
          time: 1000
        }, function () {
          $('.nav-admin-zhgl').click()
        })
      } else {
        layer.msg('修改失败')
      }
    })
    $('.fade-xgzh').hide();
  })
  //取消修改账号
  $('.btn-xg-account-number-del').on('click', function () {
    $('.fade-xgzh').hide();
  })

  //========删除账号
  $('body').on('click', '.r-tbody-admin .admin-btn-del', function () {
    var id = $(this).attr('data_id');
    $.post('/pjsys/deleteTeacher.action', {
      id: id
    }, function (data) {
      if (data === 1) {
        layer.msg('删除账户成功', {
          timer: 2000
        }, function () {
          $('.nav-admin-zhgl').click()
        })
      } else {
        layer.msg('删除账户失败,请重新尝试')
      }
    })
  })
  // 操作日志
  // $('body').on('click','.nav-admin-operation-log',function(e){
  //     $('.r-admin-operation-log').show().siblings().hide();
  //     e.stopPropagation()
  // })
  $('body .nav-admin-operation-log').on('click', function (e) {
    $.post('/pjsys/findAllLog.action', {
      page: 1
    }, function (data) {
      // console.log(data);
      totle = data.total
      laypage.render({
        elem: 'admin-operation-page',
        count: totle,
        theme: "#65daf7",
        first: '首页',
        last: '尾页',
        jump: function (obj, first) {
          $.post('/pjsys/findAllLog.action', {
            page: obj.curr
          }, function (data) {
            bjlst_page = obj.curr;
            first_page = obj.first;
            var html = '';
            for (var i = 0; i < data.datas.length; i++) {
              html += '<tr class="r-tbody-tr"><td>' + data.datas[i].teacher.username + '</td><td>' + data.datas[i].mokuai + '</td><td>' + data.datas[i].eventType + '</td><td>' + data.datas[i].time + '</td><tr>'
            }
            $('#operation-log').html(html)
          })
        }
      });
    })
    $('.r-admin-operation-log').show().siblings().hide();
    e.stopPropagation()
  })
  // 导出日志	

  laydate.render({
    elem: '#export-admin',
    range: true
  });
  // 全部导出	
  $('.export-admin-btn').on('click', function () {
    window.location.href = '/pjsys/downloadLogsAction?type=0'
    console.log('object')
  })
  $('.export-admin-time-btn').on('click', function () {
    if ($('#export-admin').val() === '') {
      layer.msg('请选择日期')
    } else {
      var time = $('#export-admin').val()
      var start = time.substring(0, 10)
      var end = time.substring(13);
      window.location.href = '/pjsys/downloadLogsAction?type=1&startDate=' + start + '&endDate=' + end
    }
  })
  //================管理员结束==========



  $(function () {
    //    ================二级菜单跳转start
    for (var i = 0; i < $('.nav-ul').children().length; i++) {
      $('.nav-ul').children().eq(i).on('click', function (e) {
        e.stopPropagation()
        $(this).children('dl').toggle(300);
        $(this).addClass('ul-bg').children('dl').addClass('dd-bg');
        $(this).siblings().removeClass('ul-bg');
        // -----切换图片
        if ($(this).children('img').attr('src') == './img/arrow.png') {
          $(this).children('img').attr('src', './img/arrow2.png')
        } else {
          $(this).children('img').attr('src', './img/arrow.png')
        }
      })
      // 二级菜单背景色
      $('.aside-ul').children().eq(i).children('dl').children('dd').on('click', function () {
        $(this).addClass('dl-bg');
        $(this).siblings().removeClass('dl-bg');
        $(this).parent().parent().siblings().children('dl').children('dd').removeClass('dl-bg')
      })
    }
    //    ================二级菜单跳转end
    // ============按姓名查找之选课排班方式
    $('body').on('click', '.find-student-xk', function () {
      $('.fade-student-subject').show();
    })
    //--------确定科目,科目消失,班级出现
    $('.fade-student-subject-btn').on('click', function () {
      $('.fade-div-student-subject').hide();
    })
    //----------取消,隐藏选课选班
    $('.fade-student-subject-btn-del').on('click', function () {
      $('.fade-student-subject').hide();
    })
    //导出
    // 按照时间段导出考勤记录
    // 
    laydate.render({
      elem: '#time-export',
      range: true
    });
    $('.time-export').click(function() {
      var time = $('#time-export').val()
      var start = time.substring(0, 10)
      var end = time.substring(13);
      window.location.href = '/pjsys/downloadStudentAttenceStartAndEnd?startTime='+ start +'&endTime='+end
    })
    // 缴费模板导出
    $(".default-excel-jf").click(function(){
      window.location.href = '/pjsys/downloadStudentPayment'
    });
    // 缴费模板导出
    var uploadInst = upload.render({
      elem: '.import-excel-jf'
      , url: '/pjsys/loadStudentPaymentExcel'
      , accept :'file'
      ,exts: 'xls'
      ,before:function(){
        layer.load();
      }
      ,done:function(res){
        layer.closeAll("loading");
        var html = ''
        for(var i=0;i<res.length;i++){
          html += '<div class="layui-form"><table class = "layui-table"> <tr> <tbody><td>' + res[i].new_stuName + "</td><td>" + res[i].flag_info + "</td></tbody></tr></table></div>";
        }
        if(html!=''){
          layer.open({
            type: 1,
            skin: "layui-layer-demo", //样式类名
            anim: 2,
            area: ["800px", "400px"],
            shadeClose: true, //开启遮罩关闭
            content: html
          });
        }else{
          layer.msg('成功', { time: 1000 })
        }
      }
      ,error:function(){
        layer.closeAll('loading');
        layer.msg('失败重新尝试')
      }
    })
    $('.r-cw-sjhz .layui-tab .layui-tab-content .layui-tab-item:eq(0) div .r-sjhz-excel').click(function () {
      window.location.href = '/pjsys/downloadMoney.action';
    })
    // 导出时时间初始化
    laydate.render({
      elem: '#export-time-xf' //指定元素
    });
    // 导出时时间初始化
    laydate.render({
      elem: '#export-time-jf' //指定元素
    });
    // 导出全部缴费
    $('.r-cw-sjhz .layui-tab .layui-tab-content .layui-tab-item:eq(1) div .r-sjhz-excel').click(function () {
      var time = $('#export-time-jf').val()
      if (time == '') {
        window.location.href = '/pjsys/download1.action?flag=3';
      } else {
        window.location.href = '/pjsys/download1.action?flag=3&dt=' + time;
      }
    })
    // 导出全部续费
    $('.r-cw-sjhz .export-xf-all').click(function () {
      var time = $('#export-time-xf').val()
      if (time == '') {
        window.location.href = '/pjsys/download1.action?flag=9';
      } else {
        window.location.href = '/pjsys/download1.action?flag=9&dt=' + time;
      }
    })
    $('.btn-class-daochu').click(function () {
      window.location.href = '/pjsys/downloadAllStudentInfo.action';
    })
    // 按照班级导出缴费
    $('.r-cw-sjhz .layui-tab .layui-tab-content .layui-tab-item:eq(1) div .r-excel').click(function () {
      var val = $('.jiaofei-div select[name="jiaofei"]').val();
      var time = $('#export-time-jf').val()
      if (val == '') {
        layer.msg('请选择班级进行导出')
      } else {
        (time == '') ? window.location.href = '/pjsys/download1.action?flag=3&classinfo_id=' + val : window.location.href = '/pjsys/download1.action?flag=3&classinfo_id=' + val + '&dt=' + time;
      }
    })
    // 按照班级导出续费
    $('.r-cw-sjhz .export-xf').click(function () {
      var time = $('#export-time-xf').val()
      var val = $('.xufei-div select[name="xufei"]').val();
      if (val == '') {
        layer.msg('请选择班级进行导出')
      } else {
        (time == '') ? window.location.href = '/pjsys/download1.action?flag=9&classinfo_id=' + val : window.location.href = '/pjsys/download1.action?flag=9&classinfo_id=' + val + '&dt=' + time;
      }
    })
    // 导出考勤记录excel
    $(".r-jw-classKq .default-kq-excel").click(function(){
      window.location.href = '/pjsys/downloadStudentAttence'; 
    });
    // 学生考勤数据导出
    $('.r-jw-kq .dc').click(function () {
      var val = $('.r-jw-kq select[name="kq-class"]').val();
      window.location.href = '/pjsys/download4.action?classinfo_id=' + val;
    })
    $('.r-cw-sjhz .layui-tab .layui-tab-content .layui-tab-item:eq(2) div .r-sjhz-excel').click(function () {
      window.location.href = '/pjsys/download2.action';
    })
    $('.r-cw-sjhz .layui-tab .layui-tab-content .layui-tab-item:eq(4) div .r-sjhz-excel').click(function () {
      window.location.href = '/pjsys/download3.action';
    })
  })
})
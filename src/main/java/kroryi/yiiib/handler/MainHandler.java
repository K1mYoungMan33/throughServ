package kroryi.yiiib.handler;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Collections;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;


import com.google.gson.Gson;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kroryi.yiiib.dao.DirectDao;
import kroryi.yiiib.dao.DirectDaoImpl;
import kroryi.yiiib.dao.MyData;
import kroryi.yiiib.dao.MyDataSerializer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Collections;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet( "/main/process.do")
public class MainHandler extends HttpServlet {
//    private final NoticeService noticeService = new NoticeService();
    private DirectDao dao = DirectDaoImpl.getInstance();

//    private final Gson gson = new Gson();
    private final Gson gson = new GsonBuilder()
        .registerTypeAdapter(MyData.class, new MyDataSerializer())
        .create();

    public void init(){}

    public void doGet(HttpServletRequest req, HttpServletResponse resp ) throws ServletException, IOException {
        try {
            process( req, resp );
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void doPost(HttpServletRequest req, HttpServletResponse resp ) throws ServletException, IOException {
        try {
            process( req, resp );
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void process(HttpServletRequest req, HttpServletResponse response ) throws ServletException, IOException, SQLException {
        //TODO: action을 파라미터에서 세션으로 변경
        String action = req.getParameter("action");
        String tableName = req.getParameter("table");
        String strWhere = req.getParameter("where");

        List<MyData> list = dao.select( tableName , strWhere );

        String employeeJson = this.gson.toJson(list);

        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");
        PrintWriter out = response.getWriter();
        out.print(employeeJson);
        out.flush();


//
////        if ("POST".equals(req.getMethod())) {
////            try (BufferedReader reader = req.getReader()) {
////                String requestData = reader.lines().collect(Collectors.joining());
//////                out.println("Received JSON data: " + requestData);
////
////                System.out.println( 111111 );
////                System.out.println( requestData );
////                // JSON 데이터를 객체로 파싱 (예시: Gson 사용)
////                // Gson 라이브러리는 Maven 또는 Gradle 등으로 추가해야 함
////                // 예: implementation 'com.google.code.gson:gson:2.8.9'
////                // import com.google.gson.Gson;
////                // YourObject yourObject = new Gson().fromJson(requestData, YourObject.class);
////            } catch (IOException e) {
////                e.printStackTrace();
////                System.out.println( 222222);
////            }
////        }
////        System.out.println( 333333);
////
//        System.out.println("getParameterNames");
//        Enumeration<String> parameterNames = req.getParameterNames();
//        while (parameterNames.hasMoreElements()) {
//            String key = parameterNames.nextElement();
//            System.out.println("[" + key + "] " + req.getParameter(key));
//        }
//
//        System.out.println("getAttributeNames");
//        Enumeration<String> attributeNames = req.getAttributeNames();
//        while (attributeNames.hasMoreElements()) {
//            String key = attributeNames.nextElement();
//            System.out.println("[" + key + "] " + req.getAttribute(key));
//        }
////
////        System.out.println( "getParameterNames," + action );
////        List<String> parameterList = Collections.list(req.getParameterNames());
////        for ( String key : parameterList )
////        {
////            System.out.println( "["+key+"] " + req.getParameter( key ) );
////        }
////
////        System.out.println( "getAttributeNames" );
////        List<String> attrList = Collections.list(req.getAttributeNames());
////        for ( String key : attrList )
////        {
////            System.out.println( "["+key+"] " + req.getAttribute( key ) );
////        }
////
//
//        System.out.println( "getSession().getAttributeNames," + action );
//        List<String> parameterList = Collections.list(req.getSession().getAttributeNames());
//        for ( String key : parameterList )
//        {
//            System.out.println( "["+key+"] " + req.getSession().getAttribute( key ) );
//        }
//
//
//
//
//        response.setContentType("application/json");
//        response.setCharacterEncoding("utf-8");
//        PrintWriter out = response.getWriter();
//
//
//
//        switch ( action ) {
//            case "notice_detail" :
//            {
//                String strNo = req.getParameter("no");
//                int iNo = Integer.parseInt(strNo);
//
////                Notice notice = noticeService.showNoticeByNo(new Notice(iNo));
////                notice.setStrDate( notice.getWriteDate() );
////
////                String employeeJson = this.gson.toJson(notice);
//
////                out.print(employeeJson);
//                out.flush();
//                return;
//            }
//            case "notice_list" :
//            {
////                List<Notice> list = noticeService.showNoticeByAll();
//
//                req.setAttribute("page", "home.jsp");
////                req.setAttribute("list", list);
//
////                for (Notice elem : list) {
////                    elem.setStrDate(elem.getWriteDate());
////                }
//
////                if ( null != list && !list.isEmpty())
//                {
//
////                    NoticeWrapper nw = new NoticeWrapper( list.get(0) );
//
//                    System.out.println( "으아아아아아아아" );
////                    for ( String elem : nw.myFunc.keySet() )
////                    {
////                        System.out.println( elem );
////                        Object obj = nw.executeGetFunction( elem );
////                        System.out.println( obj );
////                        if ( obj instanceof Integer )
////                            nw.executeSetFunction( elem, 1 );
////                        else if ( obj instanceof String )
////                            nw.executeSetFunction( elem, "1" );
////                    }
//
//                }
//
//
////                String employeeJson = this.gson.toJson(list);
//
////                out.print(employeeJson);
//                out.flush();
//                return;
//            }
//            case "notice_insert" :
//            {
//                String formData = req.getParameter( "formData" );
////                Notice newNotice = gson.fromJson( formData, Notice.class);
////                newNotice.setWriteDate( new Date() );
////                noticeService.addNotice( newNotice );
////
////                out.print(gson.toJson(newNotice));
//                out.flush();
//                return;
//            }
//            case "notice_update" :
//            {
//                String formData = req.getParameter( "formData" );
////                Notice newNotice = gson.fromJson( formData, Notice.class);
////                noticeService.modifyNotice( newNotice );
//
////                out.print(gson.toJson(newNotice));
//                out.flush();
//                return;
//            }
//            case "notice_delete" :
//            {
//                String strNo = req.getParameter("no");
//                int iNo = Integer.parseInt(strNo);
////                noticeService.removeNotice( new Notice(iNo));
//
//                out.print(gson.toJson(iNo));
//                out.flush();
//                return;
//            }
//        } // end switch
    }
}

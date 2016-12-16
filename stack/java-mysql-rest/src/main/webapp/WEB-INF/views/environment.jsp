<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="java.util.TreeMap"%>
<%@page import="java.util.Collections"%>
<%@page import="java.util.Set"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.List"%>
<%@page import="java.lang.management.ManagementFactory"%>
<%@page import="java.lang.management.RuntimeMXBean"%>
<%@page import="java.lang.ClassLoader"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Application Container Cloud Environment Variables</title>
</head>
<body>

              <h3>
                <span style="color: #333; text-decoration: none">Environment Variables</span>
              </h3>
              <table>
                <tr>
                  <th style="text-align: left">Name</th>
                  <th style="text-align: left">Value</th>
                </tr>
                <%
                  TreeMap<String, String> sortedMap = new TreeMap<String, String>();
                  sortedMap.putAll(System.getenv());
                  for (Map.Entry<String, String> f : sortedMap.entrySet()) {
                  out.println("<tr><td><b>");
                    out.println(f.getKey());
                    out.println("</b></td><td>");
                    out.println(f.getValue());
                    out.println("</td></tr>");
                  }
                  RuntimeMXBean runtimeMxBean = ManagementFactory.getRuntimeMXBean();
                  List<String> arguments = runtimeMxBean.getInputArguments();
                  String sJavaagentArg = "-javaagent option was not defined.";
                  for (String argument : arguments) {
                    if (argument.startsWith("-javaagent:")) {
                      sJavaagentArg = argument;
                      break;
                    }
                  }
                  out.println("<tr><td><b>Java Agent</b> (RuntimeMXBean)</td><td>" + sJavaagentArg + "</td></tr>");
                  try {
                    java.lang.reflect.Method m = ClassLoader.class.getDeclaredMethod("findLoadedClass",
                        new Class[] { String.class });
                    m.setAccessible(true);
                    ClassLoader cl = ClassLoader.getSystemClassLoader();
                    Object oAgent = m.invoke(cl, "oracle.apmaas.agent.instrumentation.Agent");
                    out.println("<tr><td><b>oracle.apmaas.agent.instrumentation.Agent</b></td><td>Agent is " + (oAgent != null ? "" : "not") + " loaded.</td></tr>");
                  } catch (Exception e) {
                    e.printStackTrace();
                  }
                %>

              </table>

</body>
</html>
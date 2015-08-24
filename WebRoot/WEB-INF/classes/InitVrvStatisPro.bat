@echo off

echo 正在初始化，请稍后....
echo path:%~dp0

set base=%~dp0

set class=%base%\bin
set libs=..\lib

set class_path=%class%;%libs%\activation-1.1.jar;%libs%\annogen-0.1.0.jar;%libs%\axiom-api-1.2.7.jar;%libs%\axiom-dom-1.2.7.jar;%libs%\axiom-impl-1.2.7.jar;%libs%\axis2-adb-1.4.1.jar;%libs%\axis2-adb-codegen-1.4.1.jar;%libs%\axis2-ant-plugin-1.4.1.jar;%libs%\axis2-clustering-1.4.1.jar;%libs%\axis2-codegen-1.4.1.jar;%libs%\axis2-corba-1.4.1.jar;%libs%\axis2-fastinfoset-1.4.1.jar;%libs%\axis2-java2wsdl-1.4.1.jar;%libs%\axis2-jaxbri-1.4.1.jar;%libs%\axis2-jaxws-1.4.1.jar;%libs%\axis2-jaxws-api-1.4.1.jar;%libs%\axis2-jibx-1.4.1.jar;%libs%\axis2-json-1.4.1.jar;%libs%\axis2-jws-api-1.4.1.jar;%libs%\axis2-kernel-1.4.1.jar;%libs%\axis2-metadata-1.4.1.jar;%libs%\axis2-mtompolicy-1.4.1.jar;%libs%\axis2-saaj-1.4.1.jar;%libs%\axis2-saaj-api-1.4.1.jar;%libs%\axis2-spring-1.4.1.jar;%libs%\axis2-xmlbeans-1.4.1.jar;%libs%\backport-util-concurrent-3.1.jar;%libs%\commons-codec-1.7.jar;%libs%\commons-collections-3.2.jar;%libs%\commons-fileupload-1.2.jar;%libs%\commons-httpclient-3.1.jar;%libs%\commons-io-1.4.jar;%libs%\commons-lang-2.3.jar;%libs%\commons-logging-1.1.1.jar;%libs%\ezmorph-1.0.6.jar;%libs%\geronimo-annotation_1.0_spec-1.1.jar;%libs%\geronimo-stax-api_1.0_spec-1.0.1.jar;%libs%\httpcore-4.0-beta1.jar;%libs%\httpcore-nio-4.0-beta1.jar;%libs%\jalopy-1.5rc3.jar;%libs%\jaxb-api-2.1.jar;%libs%\jaxb-impl-2.1.6.jar;%libs%\jaxb-xjc-2.1.6.jar;%libs%\jaxen-1.1.1.jar;%libs%\jettison-1.0-RC2.jar;%libs%\jibx-bind-1.1.5.jar;%libs%\jibx-run-1.1.5.jar;%libs%\json-lib-2.4-jdk15.jar;%libs%\log4j-1.2.15.jar;%libs%\mail-1.4.jar;%libs%\mex-1.4.1.jar;%libs%\mysql-connector-java-5.0.8-bin.jar;%libs%\neethi-2.0.4.jar;%libs%\poi-3.10.1-20140818.jar;%libs%\poi-examples-3.10.1-20140818.jar;%libs%\poi-excelant-3.10.1-20140818.jar;%libs%\poi-ooxml-3.10.1-20140818.jar;%libs%\poi-ooxml-schemas-3.10.1-20140818.jar;%libs%\poi-scratchpad-3.10.1-20140818.jar;%libs%\soapmonitor-1.4.1.jar;%libs%\stax-api-1.0.1.jar;%libs%\woden-api-1.0M8.jar;%libs%\woden-impl-dom-1.0M8.jar;%libs%\wsdl4j-1.6.2.jar;%libs%\wstx-asl-3.2.4.jar;%libs%\xalan-2.7.0.jar;%libs%\xercesImpl-2.8.1.jar;%libs%\xml-apis-1.3.04.jar;%libs%\xml-resolver-1.2.jar;%libs%\xmlbeans-2.3.0.jar;%libs%\xmlbeans-2.6.0.jar;%libs%\XmlSchema-1.4.2.jar;

java -classpath %class_path% cn.pro.bxy.autorun.AutoRunLoadDataTask2
@pause
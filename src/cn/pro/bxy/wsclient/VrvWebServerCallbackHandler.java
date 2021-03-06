
/**
 * VrvWebServerCallbackHandler.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.4.1  Built on : Aug 13, 2008 (05:03:35 LKT)
 */

    package cn.pro.bxy.wsclient;

    /**
     *  VrvWebServerCallbackHandler Callback class, Users can extend this class and implement
     *  their own receiveResult and receiveError methods.
     */
    public abstract class VrvWebServerCallbackHandler{



    protected Object clientData;

    /**
    * User can pass in any object that needs to be accessed once the NonBlocking
    * Web service call is finished and appropriate method of this CallBack is called.
    * @param clientData Object mechanism by which the user can pass in user data
    * that will be avilable at the time this callback is called.
    */
    public VrvWebServerCallbackHandler(Object clientData){
        this.clientData = clientData;
    }

    /**
    * Please use this constructor if you don't want to set any clientData
    */
    public VrvWebServerCallbackHandler(){
        this.clientData = null;
    }

    /**
     * Get the client data
     */

     public Object getClientData() {
        return clientData;
     }

        
           /**
            * auto generated Axis2 call back method for GetLocalRegistration method
            * override this method for handling normal response from GetLocalRegistration operation
            */
           public void receiveResultGetLocalRegistration(
                    cn.pro.bxy.wsclient.VrvWebServerStub.GetLocalRegistrationResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from GetLocalRegistration operation
           */
            public void receiveErrorGetLocalRegistration(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for GetPMoveableDiskEvent method
            * override this method for handling normal response from GetPMoveableDiskEvent operation
            */
           public void receiveResultGetPMoveableDiskEvent(
                    cn.pro.bxy.wsclient.VrvWebServerStub.GetPMoveableDiskEventResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from GetPMoveableDiskEvent operation
           */
            public void receiveErrorGetPMoveableDiskEvent(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for GetTerminalUser method
            * override this method for handling normal response from GetTerminalUser operation
            */
           public void receiveResultGetTerminalUser(
                    cn.pro.bxy.wsclient.VrvWebServerStub.GetTerminalUserResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from GetTerminalUser operation
           */
            public void receiveErrorGetTerminalUser(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for InsertLogin method
            * override this method for handling normal response from InsertLogin operation
            */
           public void receiveResultInsertLogin(
                    cn.pro.bxy.wsclient.VrvWebServerStub.InsertLoginResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from InsertLogin operation
           */
            public void receiveErrorInsertLogin(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for GetRegisteredByDeviceName method
            * override this method for handling normal response from GetRegisteredByDeviceName operation
            */
           public void receiveResultGetRegisteredByDeviceName(
                    cn.pro.bxy.wsclient.VrvWebServerStub.GetRegisteredByDeviceNameResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from GetRegisteredByDeviceName operation
           */
            public void receiveErrorGetRegisteredByDeviceName(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for SimpleSearchEx method
            * override this method for handling normal response from SimpleSearchEx operation
            */
           public void receiveResultSimpleSearchEx(
                    cn.pro.bxy.wsclient.VrvWebServerStub.SimpleSearchExResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from SimpleSearchEx operation
           */
            public void receiveErrorSimpleSearchEx(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for GetDownAndRunNum method
            * override this method for handling normal response from GetDownAndRunNum operation
            */
           public void receiveResultGetDownAndRunNum(
                    cn.pro.bxy.wsclient.VrvWebServerStub.GetDownAndRunNumResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from GetDownAndRunNum operation
           */
            public void receiveErrorGetDownAndRunNum(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for GetDomainStatusByDeviceName method
            * override this method for handling normal response from GetDomainStatusByDeviceName operation
            */
           public void receiveResultGetDomainStatusByDeviceName(
                    cn.pro.bxy.wsclient.VrvWebServerStub.GetDomainStatusByDeviceNameResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from GetDomainStatusByDeviceName operation
           */
            public void receiveErrorGetDomainStatusByDeviceName(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for GetDeviceBasicReader method
            * override this method for handling normal response from GetDeviceBasicReader operation
            */
           public void receiveResultGetDeviceBasicReader(
                    cn.pro.bxy.wsclient.VrvWebServerStub.GetDeviceBasicReaderResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from GetDeviceBasicReader operation
           */
            public void receiveErrorGetDeviceBasicReader(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for GetRegionalManagerDatabaseState method
            * override this method for handling normal response from GetRegionalManagerDatabaseState operation
            */
           public void receiveResultGetRegionalManagerDatabaseState(
                    cn.pro.bxy.wsclient.VrvWebServerStub.GetRegionalManagerDatabaseStateResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from GetRegionalManagerDatabaseState operation
           */
            public void receiveErrorGetRegionalManagerDatabaseState(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for LoginEx method
            * override this method for handling normal response from LoginEx operation
            */
           public void receiveResultLoginEx(
                    cn.pro.bxy.wsclient.VrvWebServerStub.LoginExResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from LoginEx operation
           */
            public void receiveErrorLoginEx(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for BasicReader method
            * override this method for handling normal response from BasicReader operation
            */
           public void receiveResultBasicReader(
                    cn.pro.bxy.wsclient.VrvWebServerStub.BasicReaderResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from BasicReader operation
           */
            public void receiveErrorBasicReader(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for GetADdomainUserInformationReporting method
            * override this method for handling normal response from GetADdomainUserInformationReporting operation
            */
           public void receiveResultGetADdomainUserInformationReporting(
                    cn.pro.bxy.wsclient.VrvWebServerStub.GetADdomainUserInformationReportingResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from GetADdomainUserInformationReporting operation
           */
            public void receiveErrorGetADdomainUserInformationReporting(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for IpOnLineSearch method
            * override this method for handling normal response from IpOnLineSearch operation
            */
           public void receiveResultIpOnLineSearch(
                    cn.pro.bxy.wsclient.VrvWebServerStub.IpOnLineSearchResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from IpOnLineSearch operation
           */
            public void receiveErrorIpOnLineSearch(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for SearchByName method
            * override this method for handling normal response from SearchByName operation
            */
           public void receiveResultSearchByName(
                    cn.pro.bxy.wsclient.VrvWebServerStub.SearchByNameResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from SearchByName operation
           */
            public void receiveErrorSearchByName(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for GetBasicReaderLasttime method
            * override this method for handling normal response from GetBasicReaderLasttime operation
            */
           public void receiveResultGetBasicReaderLasttime(
                    cn.pro.bxy.wsclient.VrvWebServerStub.GetBasicReaderLasttimeResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from GetBasicReaderLasttime operation
           */
            public void receiveErrorGetBasicReaderLasttime(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for GetDevicePatch method
            * override this method for handling normal response from GetDevicePatch operation
            */
           public void receiveResultGetDevicePatch(
                    cn.pro.bxy.wsclient.VrvWebServerStub.GetDevicePatchResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from GetDevicePatch operation
           */
            public void receiveErrorGetDevicePatch(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for GetBasicReaderRegistered method
            * override this method for handling normal response from GetBasicReaderRegistered operation
            */
           public void receiveResultGetBasicReaderRegistered(
                    cn.pro.bxy.wsclient.VrvWebServerStub.GetBasicReaderRegisteredResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from GetBasicReaderRegistered operation
           */
            public void receiveErrorGetBasicReaderRegistered(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for DetailSearch method
            * override this method for handling normal response from DetailSearch operation
            */
           public void receiveResultDetailSearch(
                    cn.pro.bxy.wsclient.VrvWebServerStub.DetailSearchResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from DetailSearch operation
           */
            public void receiveErrorDetailSearch(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for GetOperatingSystemByDeviceName method
            * override this method for handling normal response from GetOperatingSystemByDeviceName operation
            */
           public void receiveResultGetOperatingSystemByDeviceName(
                    cn.pro.bxy.wsclient.VrvWebServerStub.GetOperatingSystemByDeviceNameResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from GetOperatingSystemByDeviceName operation
           */
            public void receiveErrorGetOperatingSystemByDeviceName(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for BasicSearch method
            * override this method for handling normal response from BasicSearch operation
            */
           public void receiveResultBasicSearch(
                    cn.pro.bxy.wsclient.VrvWebServerStub.BasicSearchResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from BasicSearch operation
           */
            public void receiveErrorBasicSearch(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for LoginEx2 method
            * override this method for handling normal response from LoginEx2 operation
            */
           public void receiveResultLoginEx2(
                    cn.pro.bxy.wsclient.VrvWebServerStub.LoginEx2Response result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from LoginEx2 operation
           */
            public void receiveErrorLoginEx2(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for SearchByIPRangeORDeviceType method
            * override this method for handling normal response from SearchByIPRangeORDeviceType operation
            */
           public void receiveResultSearchByIPRangeORDeviceType(
                    cn.pro.bxy.wsclient.VrvWebServerStub.SearchByIPRangeORDeviceTypeResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from SearchByIPRangeORDeviceType operation
           */
            public void receiveErrorSearchByIPRangeORDeviceType(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for GetSoftwareByDeviceName method
            * override this method for handling normal response from GetSoftwareByDeviceName operation
            */
           public void receiveResultGetSoftwareByDeviceName(
                    cn.pro.bxy.wsclient.VrvWebServerStub.GetSoftwareByDeviceNameResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from GetSoftwareByDeviceName operation
           */
            public void receiveErrorGetSoftwareByDeviceName(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for Login method
            * override this method for handling normal response from Login operation
            */
           public void receiveResultLogin(
                    cn.pro.bxy.wsclient.VrvWebServerStub.LoginResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from Login operation
           */
            public void receiveErrorLogin(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for GetTerminalGroup method
            * override this method for handling normal response from GetTerminalGroup operation
            */
           public void receiveResultGetTerminalGroup(
                    cn.pro.bxy.wsclient.VrvWebServerStub.GetTerminalGroupResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from GetTerminalGroup operation
           */
            public void receiveErrorGetTerminalGroup(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for SearchByMac method
            * override this method for handling normal response from SearchByMac operation
            */
           public void receiveResultSearchByMac(
                    cn.pro.bxy.wsclient.VrvWebServerStub.SearchByMacResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from SearchByMac operation
           */
            public void receiveErrorSearchByMac(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for SearchByOnlyId method
            * override this method for handling normal response from SearchByOnlyId operation
            */
           public void receiveResultSearchByOnlyId(
                    cn.pro.bxy.wsclient.VrvWebServerStub.SearchByOnlyIdResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from SearchByOnlyId operation
           */
            public void receiveErrorSearchByOnlyId(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for LogOut method
            * override this method for handling normal response from LogOut operation
            */
           public void receiveResultLogOut(
                    cn.pro.bxy.wsclient.VrvWebServerStub.LogOutResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from LogOut operation
           */
            public void receiveErrorLogOut(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for GetDiskFreeSpaceByDeviceName method
            * override this method for handling normal response from GetDiskFreeSpaceByDeviceName operation
            */
           public void receiveResultGetDiskFreeSpaceByDeviceName(
                    cn.pro.bxy.wsclient.VrvWebServerStub.GetDiskFreeSpaceByDeviceNameResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from GetDiskFreeSpaceByDeviceName operation
           */
            public void receiveErrorGetDiskFreeSpaceByDeviceName(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for SimpleSearch method
            * override this method for handling normal response from SimpleSearch operation
            */
           public void receiveResultSimpleSearch(
                    cn.pro.bxy.wsclient.VrvWebServerStub.SimpleSearchResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from SimpleSearch operation
           */
            public void receiveErrorSimpleSearch(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for GetBasicSearchSpecificInformation method
            * override this method for handling normal response from GetBasicSearchSpecificInformation operation
            */
           public void receiveResultGetBasicSearchSpecificInformation(
                    cn.pro.bxy.wsclient.VrvWebServerStub.GetBasicSearchSpecificInformationResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from GetBasicSearchSpecificInformation operation
           */
            public void receiveErrorGetBasicSearchSpecificInformation(java.lang.Exception e) {
            }
                


    }
    
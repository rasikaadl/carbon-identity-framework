/*
 * Copyright (c) 2018, WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
 *
 * WSO2 Inc. licenses this file to you under the Apache License,
 * Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package org.wso2.carbon.identity.functions.library.mgt;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.wso2.carbon.core.AbstractAdmin;
import org.wso2.carbon.identity.functions.library.mgt.exception.FunctionLibraryManagementException;
import org.wso2.carbon.identity.functions.library.mgt.model.FunctionLibrary;


/**
 * Function library management admin service.
 */
public class FunctionLibraryManagementAdminService extends AbstractAdmin {

    private static Log log = LogFactory.getLog(FunctionLibraryManagementAdminService.class);
    private FunctionLibraryManagementService functionLibMgtService;

    /**
     * Create a function library with function library name, description and script.
     *
     * @param functionLibrary Function library
     * @throws FunctionLibraryManagementException
     */
    public void createFunctionLibrary(FunctionLibrary functionLibrary) throws FunctionLibraryManagementException {
        try {
            functionLibMgtService = FunctionLibraryManagementServiceImpl.getInstance();
            functionLibMgtService.createFunctionLibrary(functionLibrary, getTenantDomain());
        } catch (FunctionLibraryManagementException flException) {
            log.error("Error while creating function library " + functionLibrary.getFunctionLibraryName() +
                    " for tenant domain " + getTenantDomain(), flException);
            throw flException;
        }

    }

    /**
     * Get all function libraries in a tenant.
     *
     * @return A list of function libraries
     * @throws FunctionLibraryManagementException
     */
    public FunctionLibrary[] listFunctionLibraries() throws FunctionLibraryManagementException {
        try {
            functionLibMgtService = FunctionLibraryManagementServiceImpl.getInstance();
            FunctionLibrary[] functionLibraries = functionLibMgtService.listFunctionLibraries(getTenantDomain());
            return functionLibraries;
        } catch (FunctionLibraryManagementException flException) {
            log.error("Error while retrieving function libraris for tenant: " + getTenantDomain(), flException);
            throw flException;
        }
    }

    /**
     * Get a function library using function library name.
     *
     * @param functionLibraryName Name of the function library
     * @return Function library
     * @throws FunctionLibraryManagementException
     */
    public FunctionLibrary getFunctionLibrary(String functionLibraryName) throws FunctionLibraryManagementException {
        try {
            functionLibMgtService = FunctionLibraryManagementServiceImpl.getInstance();
            FunctionLibrary functionLibrary = null;
            functionLibrary = functionLibMgtService.getFunctionLibrary(functionLibraryName, getTenantDomain());
            return functionLibrary;
        } catch (FunctionLibraryManagementException flException) {
            log.error("Error while retrieving function library " + functionLibraryName +
                    " for tenant domain " + getTenantDomain(), flException);
            throw flException;
        }

    }

    /**
     * Delete an existing function library using the function library name.
     *
     * @param functionLibraryName Name of the function library
     * @throws FunctionLibraryManagementException
     */
    public void deleteFunctionLibrary(String functionLibraryName) throws FunctionLibraryManagementException {
        try {
            functionLibMgtService = FunctionLibraryManagementServiceImpl.getInstance();
            functionLibMgtService.deleteFunctionLibrary(functionLibraryName, getTenantDomain());
        } catch (FunctionLibraryManagementException flException) {
            log.error("Error while deleting function library " + functionLibraryName +
                    " for tenant domain " + getTenantDomain(), flException);
            throw flException;
        }
    }

    /**
     * Update the details of a function library.
     *
     * @param functionLibrary        Function library with new details
     * @param oldFunctionLibraryName Previous name of the function library
     * @throws FunctionLibraryManagementException
     */
    public void updateFunctionLibrary(FunctionLibrary functionLibrary, String oldFunctionLibraryName)
            throws FunctionLibraryManagementException {

        try {
            functionLibMgtService = FunctionLibraryManagementServiceImpl.getInstance();
            functionLibMgtService.updateFunctionLibrary(functionLibrary, getTenantDomain(), oldFunctionLibraryName);
        } catch (FunctionLibraryManagementException flException) {
            log.error("Error while updating function library " + oldFunctionLibraryName +
                    "for tenant domain " + getTenantDomain(), flException);
            throw flException;
        }
    }
}

/*
 * Copyright 1999-2015 dangdang.com.
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * </p>
 */

package com.dangdang.ddframe.job.lite.internal.server;

import com.dangdang.ddframe.job.lite.internal.schedule.JobRegistry;
import com.dangdang.ddframe.job.lite.internal.storage.JobNodePath;

/**
 * Elastic Job运行实例节点名称的常量类.
 * 
 * @author zhangliang
 */
public class InstanceNode {
    
    /**
     * 运行实例信息根节点.
     */
    public static final String ROOT = "instances";
    
    private static final String INSTANCES = ROOT + "/%s";
    
    private final String jobName;
    
    private final JobNodePath jobNodePath;
    
    public InstanceNode(final String jobName) {
        this.jobName = jobName;
        jobNodePath = new JobNodePath(jobName);
    }
    
    /**
     * 获取本地作业运行实例路径.
     *
     * @return 本地作业运行实例路径
     */
    public String getLocalInstanceNode() {
        return String.format(INSTANCES, JobRegistry.getInstance().getJobInstance(jobName).getJobInstanceId());
    }
    
    static String getInstanceNode(final String jobInstanceId) {
        return String.format(INSTANCES, jobInstanceId);
    }
    
    /**
     * 判断给定路径是否为本地作业运行实例路径.
     *
     * @param path 待判断的路径
     * @return 是否为本地作业运行实例路径
     */
    public boolean isLocalInstancePath(final String path) {
        return path.equals(jobNodePath.getFullPath(String.format(INSTANCES, JobRegistry.getInstance().getJobInstance(jobName).getJobInstanceId())));
    }
    
    /**
     * 判断给定路径是否为作业运行实例路径.
     *
     * @param path 待判断的路径
     * @return 是否为作业运行实例路径
     */
    public boolean isInstancePath(final String path) {
        return path.startsWith(jobNodePath.getFullPath(InstanceNode.ROOT));
    }
}
package com.Service;

import com.Entity.Job;
import com.Param.Response.JobResponse;

import java.util.List;

public interface MethodService {

    public JobResponse method(List<Job> Jobs, JobResponse jobResponses);
}

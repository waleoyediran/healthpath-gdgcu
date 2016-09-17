package com.healthpath.healthpath;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 *
 * Created by oyewale on 17/09/2016.
 */
public interface HealthService {
    @GET("/api/healthcare/")
    Call<List<Hospital>> getHospitals(@Query("coords") String coordinates);
}

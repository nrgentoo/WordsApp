package com.nrgentoo.wordsapp.network;

import com.nrgentoo.wordsapp.model.WordTask;

import java.util.List;

import retrofit.http.GET;
import retrofit.http.Query;
import rx.Observable;

/**
 * API interface
 */
public interface SkyengAPI {

    String END_POINT = "http://http://dictionary.skyeng.ru";
    String PREFIX = "/api/v1";

    /**
     * Get word tasks
     *
     * @param meaningIds word ids
     * @param width screen width
     * @return
     */
    @GET(PREFIX + "/wordtasks")
    Observable<List<WordTask>> getWords(
            @Query("meaningIds")List<Long> meaningIds,
            @Query("width") int width);
}

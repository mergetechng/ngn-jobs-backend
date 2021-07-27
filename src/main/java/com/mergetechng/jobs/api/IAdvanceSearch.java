package com.mergetechng.jobs.api;


import com.mergetechng.jobs.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.support.PageableExecutionUtils;

import java.util.List;

public interface IAdvanceSearch<TEnResult> {
     Page<TEnResult> getAllWithPageable(Query query , Pageable pageable);

     List<TEnResult> getAllWithoutPageable(Query query);
}

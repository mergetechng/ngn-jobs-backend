package com.mergetechng.jobs.services;

import com.mergetechng.jobs.commons.dto.FilterCondition;
import com.mergetechng.jobs.commons.enums.FilterOperationEnum;
import com.mergetechng.jobs.exceptions.BadRequestException;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Service for Filtering Page
 * This class is used to extract any filters requested by the client.
 */
@Service
public class FilterBuilderService {

    private static final int DEFAULT_SIZE_PAGE = 20;


    /**
     * Prepare filter condition.  extract the different filters used in the controller via @RequestParam
     *
     * @param criteria search Criteria.
     * @return a list of {@link FilterCondition}
     */
    public List<FilterCondition> createFilterCondition(String criteria) throws BadRequestException {
        List<FilterCondition> filters = new ArrayList<>();

        try {

            if (criteria != null && !criteria.isEmpty()) {

                final String FILTER_SEARCH_DELIMITER = "&";
                final String FILTER_CONDITION_DELIMITER = "\\|";

                List<String> values = split(criteria, FILTER_SEARCH_DELIMITER);
                if (!values.isEmpty()) {
                    values.forEach(x -> {
                        List<String> filter = split(x, FILTER_CONDITION_DELIMITER);
                        if (FilterOperationEnum.fromValue(filter.get(1)) != null) {
                            filters.add(new FilterCondition(filter.get(0), FilterOperationEnum.fromValue(filter.get(1)), filter.get(2)));
                        }
                    });
                }
            }

            return filters;

        } catch (Exception ex) {
            throw new BadRequestException("Cannot create condition filter " + ex.getMessage());
        }

    }


    private static List<String> split(String search, String delimiter) {
        return Stream.of(search.split(delimiter))
                .collect(Collectors.toList());
    }


    /**
     * Get request pageable. Page Request Builder. custom pageable
     *
     * @param size  the number of items to collect
     * @param page  page number
     * @param order search order filter (eg: field|ASC)
     * @return PageRequest
     */
    public PageRequest getPageable(int size, int page, String order) throws BadRequestException {

        int pageSize = (size <= 0) ? DEFAULT_SIZE_PAGE : size;
        int currentPage = (page <= 0) ? 1 : page;

        try {
            if (ObjectUtils.isNotEmpty(order)) {

                final String FILTER_CONDITION_DELIMITER = "\\|";

                List<String> values = split(order, FILTER_CONDITION_DELIMITER);
                String column = values.get(0);
                String sortDirection = values.get(1);

                if (sortDirection.equalsIgnoreCase("ASC")) {
                    return PageRequest.of((currentPage - 1), pageSize, Sort.by(Sort.Direction.ASC, column));
                } else if (sortDirection.equalsIgnoreCase("DESC")) {
                    return PageRequest.of((currentPage - 1), pageSize, Sort.by(Sort.Direction.DESC, column));
                } else {
                    throw new IllegalArgumentException(String.format("Value for param 'order' is not valid : %s , must be 'asc' or 'desc'", sortDirection));
                }

            } else {
                return PageRequest.of((currentPage - 1), pageSize);
            }
        } catch (Exception ex) {
            throw new BadRequestException("Cannot create condition filter " + ex.getMessage());
        }
    }
}
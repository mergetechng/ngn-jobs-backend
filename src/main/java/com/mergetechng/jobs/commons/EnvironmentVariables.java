package com.mergetechng.jobs.commons;

import java.util.Optional;

public final class EnvironmentVariables {
    public static final String SECRET_KEY = Optional.ofNullable(System.getenv("SECRET_KEY")).orElse("cv,$Ew;p7ZFpoctYlni]?!676ACA8AB84F8}=!MO(V1,xRB-mHv08gR#!2B3FDC142453A(w~)=y@577E92FCA4EBC9B86E9CC542C5E7B%N)~x?DXtYW*f%z4iJVq}sUUDZavD?BVzE%9%`TS'NF!");
    public static final String AMAZON_PROPERTIES_SECRET_KEY = Optional.ofNullable(System.getenv("AMAZON_PROPERTIES_SECRET_KEY")).orElse("");
    public static final String AMAZON_PROPERTIES_ACCESS_KEY = Optional.ofNullable(System.getenv("AMAZON_PROPERTIES_ACCESS_KEY")).orElse("");
}

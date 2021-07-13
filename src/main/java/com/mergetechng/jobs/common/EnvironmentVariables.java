package com.mergetechng.jobs.common;

import java.util.Optional;

public final class EnvironmentVariables {
    public static final String SECRET_KEY = Optional.ofNullable(System.getenv("SECRET_KEY")).orElse("cv,$Ew;p7ZFpoctYlni]?!676ACA8AB84F8}=!MO(V1,xRB-mHv08gR#!2B3FDC142453A(w~)=y@577E92FCA4EBC9B86E9CC542C5E7B%N)~x?DXtYW*f%z4iJVq}sUUDZavD?BVzE%9%`TS'NF!");
}

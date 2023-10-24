package com.example.springtest.sample;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

@Repository
@Qualifier("normal")//normal 이라는 이름을 가지게 된 샘
public class SampleDAOImpl implements SampleDAO{
}

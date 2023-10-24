package com.example.springtest.sample;

import lombok.RequiredArgsConstructor;
import lombok.ToString;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Log4j2
@Service
@ToString
@RequiredArgsConstructor
public class SampleServeice {
    //@Autowired
    @Qualifier("normal")
    private final SampleDAO sampleDAO;
}

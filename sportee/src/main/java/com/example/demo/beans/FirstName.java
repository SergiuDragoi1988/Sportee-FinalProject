package com.example.demo.beans;

import com.example.demo.interfaces.BeanName;
import org.springframework.stereotype.Component;

@Component
public class FirstName implements BeanName {
    @Override
    public String getName() {
        return "M";
    }
}

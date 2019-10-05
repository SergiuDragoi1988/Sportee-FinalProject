package com.example.demo.beans;

import com.example.demo.interfaces.BeanName;
import org.springframework.stereotype.Component;

@Component
public class SecondName implements BeanName {
    @Override
    public String getName() {
        return "S";
    }
}

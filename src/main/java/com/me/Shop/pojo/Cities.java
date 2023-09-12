package com.me.Shop.pojo;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Component
@Entity
@Table(name="cities")
public class Cities {
List<String> states;

public List<String> getStates() {
	return states;
}

public void setStates(List<String> states) {
	this.states = states;
}

}

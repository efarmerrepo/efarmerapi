package com.efarmer.api.efarmer.model;

import java.util.List;

import com.efarmer.api.efarmer.entity.Category;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@Data
@Builder
public class Categories {
	List<Category> categories ;

}

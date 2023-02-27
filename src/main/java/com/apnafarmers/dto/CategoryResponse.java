package com.apnafarmers.dto;

import java.util.List;

import com.apnafarmers.entity.Category;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryResponse {

	List<Category> categories;
}

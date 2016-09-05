package com.realdolmen.web.controller.imageSwitcher;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Named;

@Named
public class ImagesView {

	private List<String> images;

	@PostConstruct
	public void init() {
		images = new ArrayList<String>();
		for (int i = 1; i <= 6; i++) {
			images.add("wallpaper" + i + ".jpg");
		}
		Collections.shuffle(images);
	}

	public List<String> getImages() {
		return images;
	}
	
	
	
}
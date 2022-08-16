package com.efarmer.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;


@RestController
@Slf4j
@RequestMapping("/efarmer")
public class EfarmerController {
	
	@RequestMapping("/")
	public String print() {
	
		
		return "<body style=\"background-color:pink;\"><h1 color='pink'>  Good Morning Welcome Vipin to Pune <h1> </body>";

	}
	
//	@RequestMapping(value = "/sid", method = RequestMethod.GET,
//            produces = MediaType.IMAGE_JPEG_VALUE)

//    public void getImage(HttpServletResponse response) throws IOException {
//
//        var imgFile = new ClassPathResource("image/sid.jpg");
//
//        response.setContentType(MediaType.IMAGE_JPEG_VALUE);
//        StreamUtils.copy(imgFile.getInputStream(), response.getOutputStream());
//    }
//	
	

}

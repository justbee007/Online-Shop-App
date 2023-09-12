package com.me.Shop.Controller;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CustomerRestController
{
	@GetMapping("/cities")
	public List<String> loadcities(@RequestParam(value = "name", defaultValue = "") String name) throws IOException
	{
		List<String> val = new ArrayList<String>();
		val.add("Boston");
		val.add("Minnesota");
		System.out.println(System.getProperty("user.dir")+"\\src\\main\\resources\\static\\");

		List<String> result;
		try (Stream<String> lines = Files.lines(Paths.get(System.getProperty("user.dir")+"\\src\\main\\resources\\static\\uscities.csv"))) {
			result = lines.collect(Collectors.toList());
		}

		
		return result;
		
	}
	@GetMapping("/states")
	public List<String> loadstates() throws IOException
	{

		System.out.println(System.getProperty("user.dir")+"\\src\\main\\resources\\static\\");

		List<String> result;
		try (Stream<String> lines = Files.lines(Paths.get(System.getProperty("user.dir")+"\\src\\main\\resources\\static\\states.csv"))) {
			result = lines.collect(Collectors.toList());
		}

		
		return result;
		
	}

}

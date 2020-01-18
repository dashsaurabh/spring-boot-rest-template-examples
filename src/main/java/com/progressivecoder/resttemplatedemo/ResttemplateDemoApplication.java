package com.progressivecoder.resttemplatedemo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

@SpringBootApplication
public class ResttemplateDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(ResttemplateDemoApplication.class, args);
	}

}


@Component
class Runner implements CommandLineRunner {

	@Override
	public void run(String... args) throws Exception {


		callGetToDoWithString();
		callGetToDoWithPOJO();
		callPostToDo();
		callPutToDo();
		callDeleteToDo();
		callGetForObject();
		callGetForEntity();

	}

	private void callGetToDoWithString() {

		RestTemplate restTemplate = new RestTemplate();

		String uri = "https://jsonplaceholder.typicode.com/todos/1";

		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);

		ResponseEntity<String> result = restTemplate.exchange(uri, HttpMethod.GET, entity, String.class);

		System.out.println("###########################################");
		System.out.println("Printing GET Response in String");
		System.out.println("###########################################");

		System.out.println(result);
	}

	private void callGetToDoWithPOJO() {
		RestTemplate restTemplate = new RestTemplate();

		String uri = "https://jsonplaceholder.typicode.com/todos/1";

		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);

		ResponseEntity<ToDo> result = restTemplate.exchange(uri, HttpMethod.GET, entity, ToDo.class);

		System.out.println("###########################################");
		System.out.println("Printing GET Response in POJO");
		System.out.println("###########################################");

		System.out.println(result.toString());
	}

	private void callPostToDo() {
		RestTemplate restTemplate = new RestTemplate();

		String uri = "https://jsonplaceholder.typicode.com/todos";

		ToDo newTodo = new ToDo();
		newTodo.setUserId(1);
		newTodo.setTitle("Test Todo");
		newTodo.setCompleted(false);

		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<ToDo> entity = new HttpEntity<ToDo>(newTodo, headers);

		ResponseEntity<ToDo> result = restTemplate.exchange(uri, HttpMethod.POST, entity, ToDo.class);

		System.out.println("###########################################");
		System.out.println("Printing POST Response");
		System.out.println("###########################################");

		System.out.println(result);
	}

	private void callPutToDo() {
		RestTemplate restTemplate = new RestTemplate();

		String uri = "https://jsonplaceholder.typicode.com/todos/1";

		ToDo newTodo = new ToDo();
		newTodo.setUserId(1);
		newTodo.setId(1);
		newTodo.setTitle("Updated ToDo");
		newTodo.setCompleted(false);

		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<ToDo> entity = new HttpEntity<ToDo>(newTodo, headers);

		ResponseEntity<ToDo> result = restTemplate.exchange(uri, HttpMethod.PUT, entity, ToDo.class);

		System.out.println("###########################################");
		System.out.println("Printing PUT Response");
		System.out.println("###########################################");

		System.out.println(result);
	}

	private void callDeleteToDo() {

		RestTemplate restTemplate = new RestTemplate();

		String uri = "https://jsonplaceholder.typicode.com/todos/1";

		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);

		ResponseEntity<String> result = restTemplate.exchange(uri, HttpMethod.DELETE, entity, String.class);

		System.out.println("###########################################");
		System.out.println("Printing DELETE Response");
		System.out.println("###########################################");

		System.out.println(result);

	}

	private void callGetForObject() {

		RestTemplate restTemplate = new RestTemplate();

		String uri = "https://jsonplaceholder.typicode.com/todos/1";

		ToDo toDo = restTemplate.getForObject(uri, ToDo.class);

		System.out.println("###########################################");
		System.out.println("Printing GET FOR OBJECT Response");
		System.out.println("###########################################");

		System.out.println(toDo);
	}

	private void callGetForEntity() {

		RestTemplate restTemplate = new RestTemplate();

		String uri = "https://jsonplaceholder.typicode.com/todos/1";

		ResponseEntity result = restTemplate.getForEntity(uri, ToDo.class);

		System.out.println("###########################################");
		System.out.println("Printing GET FOR ENTITY Response");
		System.out.println("###########################################");

		System.out.println(result);
	}
}

class ToDo {

	private int userId;

	private int id;

	private String title;

	private boolean completed;

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public boolean isCompleted() {
		return completed;
	}

	public void setCompleted(boolean completed) {
		this.completed = completed;
	}

	@Override
	public String toString() {
		return "ToDo{" +
				"userId=" + userId +
				", id=" + id +
				", title='" + title + '\'' +
				", completed=" + completed +
				'}';
	}
}

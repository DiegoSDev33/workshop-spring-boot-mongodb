package com.diegosantos.workshopmongo.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="user") // estou dizendo que essa minha classe corresponde a uma coleção no mongo db
							// (collection="user") nao obrigatorio colocar, caso nao coloque o prorio metodo buscara 
							//pelo nome de referencia "user" da classe
public class User implements Serializable{
	
	/* @Id - com o id chamando aqui esta dando erro*/
	private static final long serialVersionUID = 1L;
	private String id;
	private String name;
	private String email;
	
	@DBRef(lazy = true)// lazy me garante que so virao esses dados se eles forem chamados para nao sobrecarregar uma solicitacao de usuarios por exemplo
	private List<Post> posts = new ArrayList<>();
	

	public User() {	
	}

	public User(String id, String name, String email) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public List<Post> getPosts() {
		return posts;
	}

	public void setPosts(List<Post> posts) {
		this.posts = posts;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		return Objects.equals(id, other.id);
	}
	
	
}

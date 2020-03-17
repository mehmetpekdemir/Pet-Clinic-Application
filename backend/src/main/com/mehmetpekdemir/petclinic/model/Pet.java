package com.mehmetpekdemir.petclinic.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
/**
 * Pet Entity
 * @author MEHMET PEKDEMIR 
 *
 */
@Entity
@Table(name = "pet")
public class Pet {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "name", length = 50)
	private String name;

	@Column(name = "birth_date")
	private Date birthDate;

	@ManyToOne
	@JoinColumn(name = "owner_id")
	private Owner owner;

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public Owner getOwner() {
		return owner;
	}

	public void setOwner(Owner owner) {
		this.owner = owner;
	}

	@Override
	public String toString() {
		return "Pet [id=" + id + ", name=" + name + ", birthDate=" + birthDate + ", owner=" + owner + "]";
	}

}

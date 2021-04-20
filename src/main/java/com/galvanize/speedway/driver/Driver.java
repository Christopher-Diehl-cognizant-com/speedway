package com.galvanize.speedway.driver;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "DRIVER_TABLE")
public class Driver {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@JsonIgnore
	private Long id;
	private String firstName;
	private String lastName;
	private Short age;
	private String nickName;
	private Short winds;
	private Short losses;
	@ElementCollection
	private List<Demo> cars;
}

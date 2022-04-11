package com.marco.B2TechBuildApiSpringBootMongodbSept2021.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @author ncast
 * @version 1.0
 * @created 4/10/2022
 */
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "todos")
public class TodoDto {

    @Id
    private String id;
    @NotNull(message = "todo cannot be null.")
    private String todo;
    @NotNull(message = "description cannot be null.")
    private String description;
    @NotNull(message = "completed cannot be null.")
    private Boolean completed;
    private Date createdAt, updatedAt;
}

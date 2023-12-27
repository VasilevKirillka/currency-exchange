package ru.skillbox.currency.exchange.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Currency {
    @JsonIgnore
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequence")
    @SequenceGenerator(name = "sequence", sequenceName = "create_sequence", allocationSize = 0)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @JsonIgnore
    @Column(name = "nominal")
    private Long nominal;

    @Column(name = "value")
    private Double value;

    @JsonIgnore
    @Column(name = "iso_num_code")
    private Long isoNumCode;
    @JsonIgnore

    @Column(name = "iso_char_code")
    private String isoCharCode;


}

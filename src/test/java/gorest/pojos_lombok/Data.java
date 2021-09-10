package gorest.pojos_lombok;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Data {

    private Integer id;
    private String name;
    private String email;
    private String gender;
    private String status;
}

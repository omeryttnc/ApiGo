package gorest.pojos2;

import lombok.*;

import java.util.List;

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

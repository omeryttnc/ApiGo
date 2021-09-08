package gorest.pojos2;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ApiGo {


    private Integer code;
    private Meta meta;
    private List<Data> data;
}

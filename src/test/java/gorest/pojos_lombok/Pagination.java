package gorest.pojos_lombok;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Pagination {

    private Integer total;
    private Integer pages;
    private Integer page;
    private Integer limit;
}

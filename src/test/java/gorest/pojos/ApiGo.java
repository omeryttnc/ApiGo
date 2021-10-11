package gorest.pojos;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.codehaus.jackson.annotate.JsonIgnore;


@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "code",
        "meta",
        "data"
})

public class ApiGo {

    @JsonProperty("code")
    private Integer code;
    @JsonProperty("meta")
    private Meta meta;
    @JsonProperty("data")
    private List<Data> data = null;
    private String name;
    private String email;
    private String gender;
    private String status;


    /**
     * No args constructor for use in serialization
     */
    public ApiGo() {
    }

    /**
     * @param code
     * @param data
     * @param meta
     */
    public ApiGo(Integer code, Meta meta, List<Data> data) {
        super();
        this.code = code;
        this.meta = meta;
        this.data = data;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getGender() {
        return gender;
    }

    public String getStatus() {
        return status;
    }

    public ApiGo(String name, String email, String gender, String status) {
        super();
        this.name = name;
        this.email = email;
        this.gender = gender;
        this.status = status;
    }


    @JsonProperty("code")
    public Integer getCode() {
        return code;
    }

    @JsonProperty("code")
    public void setCode(Integer code) {
        this.code = code;
    }

    @JsonProperty("meta")
    public Meta getMeta() {
        return meta;
    }

    @JsonProperty("meta")
    public void setMeta(Meta meta) {
        this.meta = meta;
    }

    @JsonProperty("data")
    public List<Data> getData() {
        return data;
    }

    @JsonProperty("data")
    public void setData(List<Data> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(ApiGo.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("code");
        sb.append('=');
        sb.append(((this.code == null) ? "<null>" : this.code));
        sb.append(',');
        sb.append("meta");
        sb.append('=');
        sb.append(((this.meta == null) ? "<null>" : this.meta));
        sb.append(',');
        sb.append("data");
        sb.append('=');
        sb.append(((this.data == null) ? "<null>" : this.data));
        sb.append(',');
        if (sb.charAt((sb.length() - 1)) == ',') {
            sb.setCharAt((sb.length() - 1), ']');
        } else {
            sb.append(']');
        }
        return sb.toString();
    }

}
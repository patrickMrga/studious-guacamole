package br.com.LL.fileprocessor.converter.reader;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Builder
public class Field {
    @Getter
    @Setter
    private String name;
    @Getter
    @Setter
    private int start;
    @Getter
    @Setter
    private int end;

}

package com.du11.coreapi.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SRDetail implements Serializable {

    private SRmgIstmInfo srmgIstmInfo;

    private SRmgSrIz srmgSrIz;

    private SRUser srUser;


}

package com.du11.coreapi.entity;

import lombok.*;

import javax.persistence.*;

@Table(name = "srmg_itsm_info")
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SRmgIstmInfo {
    @Id
    @Column(name = "sr_id")
    private String srId;

    @Column(name = "sr_nm")
    private String srNm;

    @Column(name = "app_nm")
    private String appNm;

    @Column(name = "rqp_nm")
    private String rprNm;

    @Column(name = "ch_psic_nm")
    private String chPsicNm;

    @Column(name = "ch_prg_sts_nm")
    private String chPrgStsNm;


}

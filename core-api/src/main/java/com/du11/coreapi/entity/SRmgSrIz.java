package com.du11.coreapi.entity;

import lombok.*;

import javax.persistence.*;

@Table(name = "srmg_sr_iz")
@Entity

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SRmgSrIz {
    @Id
    @Column(name = "sr_id")
    private String srId;

    @Column(name = "tsk_c")
    private String tskC;

    @Column(name = "ak_dept")
    private String akDept;

    @Column(name = "ana_empno")
    private String anaEmpno;

    @Column(name = "dev_empno")
    private String devEmpno;

    @Column(name = "ak_dt")
    private String akDt;

    @Column(name = "dev_ak_dt")
    private String devAkDt;

    @Column(name = "prg_sts")
    private String pgrSts;

    @Column(name = "fsh_dt")
    private String fshDt;

    @Column(name = "fsh_exp_dt")
    private String fshExpDt;

    @Column(name = "strtdt")
    private String strtdt;

    @Column(name = "enddt")
    private String enddt;

    @Column(name = "tf_dt")
    private String tfDt;

    @Column(name = "sr_mo")
    private String srMo;


}

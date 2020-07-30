package com.du11.coreapi.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "srmg_dev_emp_bas")
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SRUser {

    @Id
    @Column(name = "EMPNO")
    private String empno;

    @Column(name = "FNM")
    private String fnm;

    @Column(name = "GRP")
    private String grp;

    @Column(name = "MNGR_RGH_YN")
    private String mngrRghYn;

    @Column(name = "DL_YN")
    private String dlYn;

//    @Column(name = "EMPNO")
//    private String anaEmpno;
//
//    @Column(name = "FNM")
//    private String anaFnm;
//
//    @Column(name = "FNM")
//    private String dev;
}

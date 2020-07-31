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

    @Column(name = "cg_fsh_duedt")
    private String cgFshDuedt;

    @Column(name = "itsm_trs_sts_c")
    private String istmTrsStsC;

    @Column(name = "rcst_nm")
    private String rcstNm;

    @Column(name = "rcst_dtm")
    private String rcstDtm;

    @Column(name = "ak_cn")
    private String akCn;

    @Column(name = "apy_cfdc_flag")
    private String apyCfdcFlag;

    @Column(name = "apy_cfdc_pcp_nm")
    private String apyCfdcPcpNm;

    @Column(name = "apy_cfdc_procs_dtm")
    private String apyCfdcProcsDtm;

    @Column(name = "ch_rc_syncrn_dtm")
    private String chRcSyncrnDtm;

    @Column(name = "rqr_nm")
    private String rqrNm;

    @Column(name = "ch_psic_nm")
    private String chPsicNm;

    @Column(name = "ak_dept")
    private String akDept;

    @Column(name = "sr_rate")
    private String srRate;

    @Column(name = "ch_prg_sts_nm")
    private String chPrgStsNm;


}

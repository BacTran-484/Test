CREATE DATABASE IF NOT EXISTS `sr` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `sr`;

CREATE TABLE IF NOT EXISTS `rcbz_code_dtl_info` (
    `CD_ID` VARCHAR(6) NOT NULL,
    `DTL_CD_ID` VARCHAR(100) NOT NULL,
    `DTL_CD_NM` VARCHAR(100) NOT NULL DEFAULT ' ',
    `RGR_ENO` VARCHAR(13) NOT NULL DEFAULT ' ',
    `RG_DT` VARCHAR(8) NOT NULL DEFAULT ' ',
    `DLR_ENO` VARCHAR(13) NOT NULL DEFAULT ' ',
    `DL_DT` VARCHAR(8) NOT NULL DEFAULT ' ',
    PRIMARY KEY (`CD_ID`, `DTL_CD_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS `srmg_emp_rgh` (
    `RGH_ID` VARCHAR(4) NOT NULL,
    `RHG_NM` VARCHAR(100) NOT NULL DEFAULT ' ',
    `VL_YN` VARCHAR(1) NOT NULL DEFAULT ' ',
    `SYS_FST_RG_TS` TIMESTAMP(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6),
    `SYS_FST_RG_USID` VARCHAR(50) NOT NULL DEFAULT ' ',
    `SYS_LT_CH_TS` TIMESTAMP(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6),
    `SYS_LT_CH_USID` VARCHAR(50) NOT NULL DEFAULT ' ',
    PRIMARY KEY (`RGH_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

CREATE TABLE IF NOT EXISTS `srmg_emp_rle` (
    `RLE_ID` VARCHAR(4) NOT NULL,
    `RGH_ID` VARCHAR(4) NOT NULL,
    `APY_STRTDT` VARCHAR(8) NOT NULL,
    `APY_ENDDT` VARCHAR(8) NOT NULL DEFAULT ' ',
    `RLE_NM` VARCHAR(100) NOT NULL DEFAULT ' ',
    `SYS_FST_RG_TS` TIMESTAMP(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6),
    `SYS_FST_RG_USID` VARCHAR(50) NOT NULL DEFAULT ' ',
    `SYS_LT_CH_TS` TIMESTAMP(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6),
    `SYS_LT_CH_USID` VARCHAR(50) NOT NULL DEFAULT ' ',
    PRIMARY KEY (`RLE_ID`, `RGH_ID`, `APY_STRTDT`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS `srmg_emp_rle_rgh_bas` (
    `EMPNO` VARCHAR(13) NOT NULL,
    `DV_C` VARCHAR(1) NOT NULL,
    `RLE_RGH_ID` VARCHAR(4) NOT NULL,
    `APY_STRTDT` VARCHAR(8) NOT NULL,
    `APY_ENDDT` VARCHAR(8) NOT NULL DEFAULT ' ',
    `SYS_FST_RG_TS` TIMESTAMP(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6),
    `SYS_FST_RG_USID` VARCHAR(50) NOT NULL DEFAULT ' ',
    `SYS_LT_CH_TS` TIMESTAMP(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6),
    `SYS_LT_CH_USID` VARCHAR(50) NOT NULL DEFAULT ' ',
    PRIMARY KEY (`EMPNO`, `DV_C`,`RLE_RGH_ID`, `APY_STRTDT`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS `srmg_login_inf` (
    `EMPNO` VARCHAR(13) NOT NULL,
    `PW` VARCHAR(22) NOT NULL DEFAULT ' ',
    `SYS_FST_RG_TS` TIMESTAMP(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6),
    `SYS_FST_RG_USID` VARCHAR(50) NOT NULL DEFAULT ' ',
    `SYS_LT_CH_TS` TIMESTAMP(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6),
    `SYS_LT_CH_USID` VARCHAR(50) NOT NULL DEFAULT ' ',
    PRIMARY KEY (`EMPNO`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

CREATE TABLE IF NOT EXISTS `srmg_dev_emp_bas` (
    `EMPNO` VARCHAR(13) NOT NULL,
    `FNM` VARCHAR(22) NOT NULL DEFAULT ' ',
    `GRP` VARCHAR(1) NOT NULL DEFAULT ' ',
    `MNGR_RGH_YN` VARCHAR(1) NOT NULL DEFAULT ' ',
    `DL_YN` VARCHAR(1) NOT NULL DEFAULT ' ',
    PRIMARY KEY (`EMPNO`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- test.srmg_dcby_hh_iz definition

CREATE TABLE `srmg_dcby_hh_iz` (
  `sr_id` varchar(14) NOT NULL DEFAULT '',
  `sml_sn` int(2) NOT NULL DEFAULT 1 COMMENT 'Simulation serial number',
  `asn_dt` varchar(8) NOT NULL DEFAULT '',
  `empno` varchar(13) NOT NULL DEFAULT '',
  `asn_hh` int(22) NOT NULL DEFAULT 0,
  `aldus_hh` int(22) NOT NULL DEFAULT 0,
  PRIMARY KEY (`sml_sn`,`sr_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='SR Daily man hour itemization';


-- test.srmg_dev_sourc_iz definition

CREATE TABLE `srmg_dev_sourc_iz` (
  `sr_id` varchar(14) NOT NULL DEFAULT '',
  `sourc_tp_c` varchar(8) NOT NULL DEFAULT '',
  `sourc_ct` int(22) NOT NULL DEFAULT 0,
  `sourc_knd_c` varchar(1) NOT NULL DEFAULT '',
  PRIMARY KEY (`sr_id`,`sourc_tp_c`,`sourc_ct`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


-- test.srmg_itsm_info definition

CREATE TABLE `srmg_itsm_info` (
  `sr_id` varchar(14) NOT NULL DEFAULT '',
  `sr_nm` varchar(2000) NOT NULL DEFAULT '',
  `ch_prg_sts_c` varchar(10) NOT NULL DEFAULT '',
  `ch_prg_sts_nm` varchar(100) NOT NULL DEFAULT '',
  `sv_cal_tf_no` varchar(14) NOT NULL DEFAULT '',
  `app_c` varchar(3) NOT NULL DEFAULT '',
  `app_nm` varchar(100) NOT NULL DEFAULT '',
  `ch_rqr_nm` varchar(100) NOT NULL DEFAULT '',
  `cg_fsh_duedt` date NOT NULL,
  `itsm_trs_sts_c` varchar(10) NOT NULL,
  `rcst_nm` varchar(100) NOT NULL,
  `rcst_dtm` date NOT NULL,
  `ak_cn` varchar(4000) NOT NULL,
  `apy_cfdc_flag` varchar(1) NOT NULL,
  `apy_cfdc_pcp_nm` varchar(100) NOT NULL,
  `apy_cfdc_procs_dtm` date NOT NULL,
  `ch_rc_syncrn_dtm` date NOT NULL,
  `sys_fst_rg_ts` timestamp NOT NULL DEFAULT current_timestamp(),
  `sys_fst_rg_usid` varchar(50) NOT NULL DEFAULT '',
  `sys_lt_ch_ts` timestamp NOT NULL DEFAULT current_timestamp(),
  `sys_lt_ch_usid` varchar(50) NOT NULL DEFAULT '',
  `rqr_nm` varchar(150) NOT NULL,
  `ch_psic_nm` varchar(150) NOT NULL,
  `ak_dept` varchar(100) NOT NULL,
  `sr_rate` varchar(1) NOT NULL,
  PRIMARY KEY (`sr_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='SR''s basic information in ITSM system. Table to get SR information from ITSM (system already in use)';


-- test.srmg_sr_inqr definition

CREATE TABLE `srmg_sr_inqr` (
  `sr_id` varchar(14) NOT NULL DEFAULT '',
  `tsk_c` varchar(3) NOT NULL DEFAULT '',
  `ak_dept` varchar(100) NOT NULL,
  `ana_dt` varchar(8) NOT NULL DEFAULT '',
  `tf_duedt` varchar(8) NOT NULL DEFAULT '',
  `tsk_procs_on` varchar(4000) NOT NULL DEFAULT '',
  `osys_ecn` varchar(2) NOT NULL DEFAULT '',
  `secu_exmnt_yn` varchar(1) NOT NULL DEFAULT '',
  `itsm_apy_yn` varchar(1) NOT NULL DEFAULT '',
  `prr_cnf_empno` varchar(13) NOT NULL DEFAULT '',
  `rg_empno` varchar(13) NOT NULL DEFAULT '',
  `rg_dt` varchar(8) NOT NULL DEFAULT '',
  `asn_sts` varchar(1) NOT NULL DEFAULT '',
  `asn_dv` varchar(1) NOT NULL DEFAULT '',
  `dev_grd` varchar(5) NOT NULL DEFAULT '',
  `sr_mo` varchar(1000) NOT NULL DEFAULT '',
  `sys_fst_rg_ts` timestamp NOT NULL DEFAULT current_timestamp(),
  `sys_fst_rg_usid` varchar(50) NOT NULL DEFAULT '',
  `sys_lt_ch_ts` timestamp NOT NULL DEFAULT current_timestamp(),
  `sys_lt_ch_usid` varchar(50) NOT NULL DEFAULT '',
  `hop_psic_empno` varchar(13) NOT NULL DEFAULT '',
  `aprvr_empno` varchar(113) NOT NULL DEFAULT '',
  `sr_ld_tp` varchar(2) NOT NULL DEFAULT '',
  `sr_dev_suj_dv_c` varchar(150) NOT NULL DEFAULT '',
  PRIMARY KEY (`sr_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='SR inquiry';


-- test.srmg_sr_iz definition

CREATE TABLE `srmg_sr_iz` (
  `sr_id` varchar(14) NOT NULL DEFAULT '',
  `tsk_c` varchar(3) NOT NULL DEFAULT '' COMMENT 'task code',
  `ak_dept` varchar(50) NOT NULL DEFAULT '' COMMENT 'Request Department',
  `ana_empno` varchar(13) NOT NULL DEFAULT '' COMMENT 'Employee No of Analyst',
  `dev_empno` varchar(13) NOT NULL DEFAULT '' COMMENT 'Employee No of developer',
  `ak_dt` varchar(8) NOT NULL DEFAULT '' COMMENT 'Request Date',
  `prg_sts` varchar(1) NOT NULL DEFAULT '' COMMENT 'Progress Status',
  `asn_dt` varchar(8) NOT NULL DEFAULT '' COMMENT 'Assignement Date',
  `dev_ak_dt` varchar(8) NOT NULL DEFAULT '' COMMENT 'Development Request Date',
  `strtdt` varchar(8) NOT NULL DEFAULT '' COMMENT 'Start Date',
  `enddt` varchar(8) NOT NULL DEFAULT '' COMMENT 'End Date',
  `tf_dt` varchar(8) NOT NULL DEFAULT '' COMMENT 'Transfer Date',
  `fsh_dt` varchar(8) NOT NULL DEFAULT '' COMMENT 'Finished Date',
  `fsh_exp_dt` varchar(8) NOT NULL DEFAULT '' COMMENT 'Finished expectation date',
  `sr_mo` varchar(1000) NOT NULL DEFAULT '' COMMENT 'SR Memo',
  PRIMARY KEY (`sr_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


-- test.srmg_sr_pg_dtl definition

CREATE TABLE `srmg_sr_pg_dtl` (
  `sr_id` varchar(14) NOT NULL DEFAULT '',
  `pgm_knd` varchar(8) NOT NULL DEFAULT '',
  `pgm_new_ecn` varchar(2) NOT NULL DEFAULT '',
  `pgm_ch_ecn` varchar(2) NOT NULL DEFAULT '',
  `pgm_dfdg` varchar(2) NOT NULL DEFAULT '',
  `pgm_mo` varchar(1000) NOT NULL DEFAULT '',
  PRIMARY KEY (`sr_id`,`pgm_knd`)
) ENGINE=InnoDB DEFAULT CHARSET= utf8;
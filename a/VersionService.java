package com.godinsec.core.service;

import java.util.Collections;
import java.util.List;

import org.apache.log4j.Logger;

import com.godinsec.core.dao.IVersionDAO;
import com.godinsec.core.iservice.IVersionService;
import com.godinsec.core.pack.VersionPack;
import com.godinsec.core.pojo.Version;
import com.godinsec.web.vo.VersionVO;
/**
 * 
 * @author zjj wzx
 * 2014-12-04
 *
 */
public class VersionService implements IVersionService {
	
	private Logger log = Logger.getLogger(this.getClass());
	private IVersionDAO versionDAO;
	
	@Override
	public VersionVO getVersionVO(int versionNo)
	{
		log.debug("getVersionVO method started!");
		VersionVO versionVO = new VersionVO();
		List<Version> versions = versionDAO.findByHqlWhere(VersionPack.packVersionQuery(versionNo));
		
		if(versions.size() == 1 && versions.get(0).getVersionNo() == versionNo)
		{
			versionVO.setFresh("1");
			versionVO.setVersion(versions.get(0));
			versionVO.getVersion().setMustFlag("0");
		}
		else
		{
			versionVO.setFresh("0");
			Collections.reverse(versions);
			versionVO.setVersion(versions.get(0));
			for(Version version : versions)
			{
				if(version.getMustFlag().equals("1") && version.getVersionNo() != versionNo){
					versionVO.getVersion().setMustFlag("1");			
				    break;
				}
			}
		}
		return versionVO;
	}

	public IVersionDAO getVersionDAO() {
		return versionDAO;
	}

	public void setVersionDAO(IVersionDAO versionDAO) {
		this.versionDAO = versionDAO;
	}

}

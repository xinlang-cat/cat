package com.xinlang.zly_xyx.cat_manage_backend.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Set;

import com.xinlang.zly_xyx.cat_manage_backend.dao.MenuDao;
import com.xinlang.zly_xyx.cat_manage_backend.dao.RoleMenuDao;
import com.xinlang.zly_xyx.cat_manage_backend.model.Menu;
import com.xinlang.zly_xyx.cat_manage_backend.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;


import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class MenuServiceImpl implements MenuService {

	@Autowired
	private MenuDao menuDao;
	@Autowired
	private RoleMenuDao roleMenuDao;

	@Transactional
	@Override
	public void save(Menu menu) {
		menu.setCreateTime(new Date());
		menu.setUpdateTime(menu.getCreateTime());

		menuDao.save(menu);
		log.info("新增菜单：{}", menu);
	}

	@Transactional
	@Override
	public void update(Menu menu) {
		menu.setUpdateTime(new Date());

		menuDao.update(menu);
		log.info("修改菜单：{}", menu);
	}

	@Transactional
	@Override
	public void delete(Long id) {
		Menu menu = menuDao.findById(id);

		menuDao.deleteByParentId(id);
		menuDao.delete(id);
		roleMenuDao.delete(null, id);

		log.info("删除菜单：{}", menu);
	}

	/**
	 * 给角色设置菜单<br>
	 * 我们这里采用先删除后插入，这样更简单
	 *
	 * @param roleId
	 * @param menuIds
	 */
	@Transactional
	@Override
	public void setMenuToRole(Long roleId, Set<Long> menuIds) {
		roleMenuDao.delete(roleId, null);

		if (!CollectionUtils.isEmpty(menuIds)) {
			menuIds.forEach(menuId -> {
				roleMenuDao.save(roleId, menuId);
			});
		}
	}

	@Override
	public List<Menu> findByRoles(Set<Long> roleIds) {
		return roleMenuDao.findMenusByRoleIds(roleIds);
	}

	@Override
	public List<Menu> findAll() {
		return menuDao.findAll();
	}

	@Override
	public Menu findById(Long id) {
		return menuDao.findById(id);
	}

	@Override
	public Set<Long> findMenuIdsByRoleId(Long roleId) {
		return roleMenuDao.findMenuIdsByRoleId(roleId);
	}

}

import React from 'react';
import MenuItem from 'app/shared/layout/menus/menu-item';
import { DropdownItem } from 'reactstrap';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { NavDropdown } from './menu-components';

const adminMenuItems = () => (
  <>
    <MenuItem icon="users" to="/admin/user-management">
      Управление пользователями
    </MenuItem>
    <MenuItem icon="tachometer-alt" to="/admin/metrics">
      Метрики
    </MenuItem>
    <MenuItem icon="heart" to="/admin/health">
      Состояние
    </MenuItem>
    <MenuItem icon="cogs" to="/admin/configuration">
      Настройки
    </MenuItem>
    <MenuItem icon="tasks" to="/admin/logs">
      Логи
    </MenuItem>
    {/* jhipster-needle-add-element-to-admin-menu - JHipster will add entities to the admin menu here */}
  </>
);

const openAPIItem = () => (
  <MenuItem icon="book" to="/admin/docs">
    API
  </MenuItem>
);

const databaseItem = () => (
  <DropdownItem tag="a" href="http://localhost:8092/" target="_tab">
    <FontAwesomeIcon icon="database" fixedWidth /> База данных
  </DropdownItem>
);

export const AdminMenu = ({ showOpenAPI, showDatabase }) => (
  <NavDropdown icon="users-cog" name="Администрирование" id="admin-menu" data-cy="adminMenu">
    {adminMenuItems()}
    {showOpenAPI && openAPIItem()}

    {showDatabase && databaseItem()}
  </NavDropdown>
);

export default AdminMenu;
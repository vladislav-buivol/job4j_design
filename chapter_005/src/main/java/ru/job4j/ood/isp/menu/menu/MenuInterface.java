package ru.job4j.ood.isp.menu.menu;

import ru.job4j.ood.isp.menu.element.Header;
import ru.job4j.ood.isp.menu.Store;

public interface MenuInterface extends Store<Header> {

    /**
     * @return first header
     */
    Header head();
}

package ru.job4j.ood.isp.menu.menu;

import ru.job4j.ood.isp.menu.action.Action;
import ru.job4j.ood.isp.menu.action.Interacting;
import ru.job4j.ood.isp.menu.element.Header;
import ru.job4j.ood.isp.menu.print.PrettyPrintable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

public class Menu implements MenuInterface, PrettyPrintable, Interacting {

    private final Header head;
    private final LinkedHashMap<String, Header> headers = new LinkedHashMap<>();
    private final HashMap<Integer, Action> actions = new HashMap<>();
    private static final String MARKER = ">";

    public Menu(Header head) {
        this.head = head;
        headers.put(head.getID(), head);
    }

    @Override
    public Header head() {
        return head;
    }

    @Override
    public void add(Header header) {
        headers.put(header.getID(), header);
    }

    @Override
    public Header getById(String id) {
        Header res = headers.get(id);
        if (res != null) {
            return res;
        } else {
            if (headers.get(id) == null) {
                for (Header h : headers.values()) {
                    res = h.getById(id);
                    if (res != null) {
                        return res;
                    }
                }
            }
        }
        return null;
    }

    @Override
    public List<Header> getAll() {
        return new ArrayList<>(headers.values());
    }

    @Override
    public String toString() {
        return "Menu{" + "head=" + head + ", headersMap=" + headers + ", actions=" + actions + '}';
    }

    @Override
    public String prettyPrint() {
        StringBuilder sb = new StringBuilder();
        for (Header head : headers.values()) {
            sb.append(prettyPrint(head, MARKER));
        }
        return sb.toString();
    }

    private String prettyPrint(Header head, String marker) {
        StringBuilder sb = new StringBuilder();
        if (head.getAll().size() == 0) {
            appendHead(head, marker, sb);
            return sb.toString();
        } else {
            appendHead(head, marker, sb);
            marker += MARKER;
            for (Header h : head.getAll()) {
                sb.append(prettyPrint(h, marker));
            }
        }
        return sb.toString();
    }

    private void appendHead(Header head, String marker, StringBuilder sb) {
        sb.append(marker);
        sb.append(head);
        sb.append(String.format(" (id:%s)", head.getID()));
        sb.append("\n");
    }

    @Override
    public void addAction(Action action) {
        actions.put(action.getId(), action);
    }

    @Override
    public List<Action> availableActions() {
        return new ArrayList<>(actions.values());
    }
}

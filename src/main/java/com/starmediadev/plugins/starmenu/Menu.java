package com.starmediadev.plugins.starmenu;

import com.starmediadev.plugins.starmcutils.util.MCUtils;
import com.starmediadev.utils.Pair;
import com.starmediadev.utils.collection.IncrementalMap;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

public class Menu implements InventoryHolder {
    //TODO Maybe store filler slots and elements separately and handle those after?
    
    protected JavaPlugin plugin; //The plugin that registered this menu
    protected String name; //Can be used to get from a cache/used by other plugins if needed/wanted
    protected String title; //The title for this menu, this is displayed in the title of the menu in game
    protected int rows; //Must be multiplied by 9
    protected IncrementalMap<Element> elements = new IncrementalMap<>(); //All elements provided to this menu. This is used to build pages
    protected Map<Integer, Slot> slots = new TreeMap<>(); //All slots for the menu, this is auto-generated and is used for setting and building the menu
    protected int currentPage = 1; //The current page that the menu is on. 
    
    public Menu(JavaPlugin plugin, String name, String title, int rows) {
        this.plugin = plugin;
        this.name = name;
        this.title = title;
        this.rows = rows;
        
        if (rows > 6) {
            throw new IllegalArgumentException("Too many rows for the menu " + name);
        }
        int totalSlots = rows * 9;
        for (int i = 0; i < totalSlots; i++) {
            slots.put(i, new Slot(i));
        }
    }
    
    public void addElements(Element... elements) {
        for (Element element : elements) {
            addElement(element);
        }
    }
    
    public void setFillerRange(Material material, int from, int to) {
        for (int i = from; i <= to; i++) {
            Slot slot = this.slots.get(i);
            if (slot.getElement() == null) {
                FillerElement element = new FillerElement(material, i);
                slot.setElement(element);
                addElement(element);
            }
        }
    }
    
    public void setFillerSlots(Material material, int... slots) {
        for (int s : slots) {
            Slot slot = this.slots.get(s);
            if (slot.getElement() == null) {
                FillerElement element = new FillerElement(material, s);
                slot.setElement(element);
                addElement(element);
            }
        }
    }

    public Inventory getInventory() {
        if (currentPage < 1) {
            currentPage = 1;
        }
        int invSize = rows * 9;
        Inventory inv = Bukkit.createInventory(this, invSize, MCUtils.color(title));
        
        var filtered = filterElements();
        var staticElements = filtered.getValue2();
        var nonStaticElements = filtered.getValue1();

        IncrementalMap<Element> fillerElements = new IncrementalMap<>();
        IncrementalMap<Element> normalStaticElements = new IncrementalMap<>();
        for (Element staticElement : staticElements) {
            if (staticElement instanceof FillerElement) {
                fillerElements.add(staticElement);
            } else {
                normalStaticElements.add(staticElement);
            }
        }

        for (Element element : normalStaticElements.values()) {
            slots.get(element.getStaticIndex()).setElement(element);
        }

        for (Element element : fillerElements.values()) {
            Slot slot = slots.get(element.getStaticIndex());
            if (slot.getElement() == null || !slot.getElement().isStatic()) {
                slot.setElement(element);
            }
        }

        int pageSize = invSize - staticElements.size();
        int totalPages = (int) Math.ceil(nonStaticElements.size() / (pageSize * 1.0));
        
        int elementStart = (currentPage - 1) * pageSize;
        for (Slot slot : this.slots.values()) {
            if (slot.getElement() == null || !slot.getElement().isStatic()) {
                slot.setElement(nonStaticElements.get(elementStart++));
            }
        }
        
        slots.forEach((index, slot) -> {
            if (slot.getElement() != null) {
                inv.setItem(index, slot.getElement().getItemStack());
            }
        });
        return inv;
    }
    
    public void addElement(Element element) {
        this.elements.add(element);
    }
    
    public void removeElement(int index) {
        this.elements.remove(index);
    }
    
    public Slot getSlot(int index) {
        return this.slots.get(index);
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }
    
    public int getTotalPages() {
        int invSize = rows * 9;
        var filtered = filterElements();
        
        var normalElements = filtered.getValue1();
        var staticElements = filtered.getValue2();
        
        System.out.println("Total normal elements " + normalElements.size());
        System.out.println("Total static elements " + staticElements.size());
        
        int pageSize = invSize - staticElements.size();
        System.out.println("Page Size " + pageSize);
        return (int) Math.ceil(normalElements.size() / (pageSize * 1.0));
    }
    
    protected Pair<NormalElements, StaticElements> filterElements() {
        int invSize = rows * 9;
        var normalElements = new NormalElements();
        var staticElements = new StaticElements();
        System.out.println("Total Elements " + this.elements.size());
        for (Element value : this.elements.values()) {
            if (value.isStatic()) {
                staticElements.add(value);
            } else {
                normalElements.add(value);
            }
        }
        return new Pair<>(normalElements, staticElements);
    }
    
    protected static class ElementFilter implements Iterable<Element> {
        IncrementalMap<Element> elements = new IncrementalMap<>();
        public void add(Element element) {
            elements.add(element);
        }
        public IncrementalMap<Element> getElements() {
            return elements;
        }
        
        public Iterator<Element> iterator() {
            return elements.values().iterator();
        }
        
        public int size() {
            return elements.size();
        }
        
        public Element get(int index) {
            return elements.get(index);
        }
    }
    
    protected static class StaticElements extends ElementFilter {
        
    }
    
    protected static class NormalElements extends ElementFilter {
        
    }
}

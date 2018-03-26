package com.softuni.residentEvil.models.view.pagination;

import com.softuni.residentEvil.entities.Virus;
import org.springframework.data.domain.Page;

public class AllVirusesViewModel {
    private Page<Virus> viruses;

    private long totalPageCount;

    public Page<Virus> getViruses() {
        return viruses;
    }

    public void setViruses(Page<Virus> viruses) {
        this.viruses = viruses;
    }

    public long getTotalPageCount() {
        return totalPageCount;
    }

    public void setTotalPageCount(long totalPageCount) {
        this.totalPageCount = totalPageCount;
    }
}

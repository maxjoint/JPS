package ru.jug.jps.dto;

public class ParticipantSearchForm {
    private String searchValue;

    public ParticipantSearchForm() {
    }

    public String getSearchValue() {
        return searchValue;
    }

    public void setSearchValue(String searchValue) {
        this.searchValue = searchValue;
    }

    @Override
    public String toString() {
        return "ParticipantSearchForm{" +
                ", searchValue='" + searchValue + '\'' +
                '}';
    }
}

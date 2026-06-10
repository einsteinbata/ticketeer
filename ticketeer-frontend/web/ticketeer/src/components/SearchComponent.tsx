import React from 'react';
import './SearchComponent.css';

const SearchComponent: React.FC = () => {
    return (
        <div className='search-container'>
            <div className='search-field-container'>
                <p className='search-field'>Data Inicial: </p>
                <p className='search-field-content'>14 Out</p>
            </div>
            <div className='search-field-container'>
                <p className='search-field'>Data Final: </p>
                <p className='search-field-content'>16 Set</p>
            </div>
            <div className='search-field-container'>
                <p className='search-field'>Cidade: </p>
                <p className='search-field-content'>Londres</p>
            </div>
            <div className='search-button-container'>
                <p className='search-field'>Pesquisar</p>
            </div>
        </div>
    )
}

export default SearchComponent;
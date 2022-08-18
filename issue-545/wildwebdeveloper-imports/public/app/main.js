// With relative imports, find references on for instance getSVGCoords finds references in this file and in screen.js
//import { getSVGCoords, getElementPageBBox, getMainSvg } from '../bundles/screen.js';
//import { parseInfo, getContainerChildren } from "../bundles/api.js";

// With absolute imports, find references on for instance getSVGCoords only find references in this file:
import { getSVGCoords, getElementPageBBox, getMainSvg } from '/public/bundles/screen.js';
import { parseInfo, getContainerChildren } from "/public/bundles/api.js";


export function app() {
    getSVGCoords();
    getElementPageBBox();
    getMainSvg();
    parseInfo();
    getContainerChildren();
    console.log("app");
}
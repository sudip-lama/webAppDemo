var error = 0;
var dateSeparator = '-';
var timeSeparator = ':';

function getKeyCode(e) { // v1.0
    if (window.event)
        return window.event.keyCode;
    else if (e)
        return e.which;
    else
        return null;
}

function keyRestrict(node, e, validchars) { // v3.0
    var key = '', keychar = '';
    key = getKeyCode(e);
    if (key === null)
        return true;
    keychar = String.fromCharCode(key);
    keychar = keychar.toLowerCase();
    validchars = validchars.toLowerCase();
    if (validchars.indexOf(keychar) !== -1) {
        node.className = "validation-passed";
        error--;
        return true;
    }
    if (key === null || key === 0 || key === 8 || key === 9 || key === 13 || key === 27) {
        node.className = "validation-passed";
        error++;
        return true;
    }
    node.className = "validation-failed";
    error++;
    return false;
}

function keyRestrictForAlphaNumeric(node, e) { // v3.0
    var key = '', validchars = 'abcdefghijklmnopqrstuvwxyz ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890', keychar = '';
    key = getKeyCode(e);
    if (key === null)
        return true;
    keychar = String.fromCharCode(key);
    keychar = keychar.toLowerCase();
    validchars = validchars.toLowerCase();
    if (validchars.indexOf(keychar) !== -1) {
        node.className = "validation-passed";
        error--;
        return true;
    }
    if (key === null || key === 0 || key === 8 || key === 9 || key === 13 || key === 27) {
        node.className = "validation-passed";
        error--;
        return true;
    }
    node.className = "validation-failed";
    error++;
    return false;
}

function keyRestrictForEmailId(node, e) { // v3.0
    var key = '', validchars = 'abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890-@._', keychar = '';
    key = getKeyCode(e);
    if (key === null)
        return true;
    keychar = String.fromCharCode(key);
    keychar = keychar.toLowerCase();
    validchars = validchars.toLowerCase();
    if (validchars.indexOf(keychar) !== -1) {
        node.className = "validation-passed";
        error--;
        return true;
    }
    if (key === null || key === 0 || key === 8 || key === 9 || key === 13 || key === 27) {
        document.getElementById(node).className = "validation-passed";
        node.className = "validation-passed";
        error--;
        return true;
    }
    document.getElementById(node).focus();
    document.getElementById(node).className = "validation-failed";
    node.className = "validation-failed";
    error++;
    return false;
}

function validateEmail(node, e) {
    var re = /^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
    if (!re.test(node.value)) {
        alert('Invalid Email');
        node.focus();
        return false;
    }
    return true;
}

function intonly(node, e) {
    return keyRestrict(node, e, '1234567890');
}

function floatonly(node, e) {
    return keyRestrict(node, e, '1234567890.');
}

function mathonly(node, e) {
    return keyRestrict(node, e, '1234567890.+-*/%<>=()[]{}');
}

function textonly(node, e) {
    return keyRestrict(node, e, 'abcdefghijklmnopqrstuvwxyz ABCDEFGHIJKLMNOPQRSTUVWXYZ.?');
}

function dateonly(node, e) {
    return keyRestrict(node, e, '1234567890-');
}

function autoHashDate(dateNode, e) {
    if (dateNode === null && dateNode.value.length === 0)
        return;
    var unicode = e.keyCode ? e.keyCode : e.charCode;

    if (unicode !== 8) {
        if (dateNode.value.length === 4) {
            dateNode.value = dateNode.value + dateSeparator;
        } else if (dateNode.value.length === 7) {
            dateNode.value = dateNode.value + dateSeparator;
        }
    }
    dateNode.focus();
}

function autoHashDateTime(dateNode, e) {
    if (dateNode === null && dateNode.value.length === 0)
        return;
    var unicode = e.keyCode ? e.keyCode : e.charCode;

    if (unicode !== 14) {
        if (dateNode.value.length === 4) {
            dateNode.value = dateNode.value + dateSeparator;
        } else if (dateNode.value.length === 7) {
            dateNode.value = dateNode.value + dateSeparator;
        } else if (dateNode.value.length === 10) {
            dateNode.value = dateNode.value + ' ';
        } else if (dateNode.value.length === 13) {
            dateNode.value = dateNode.value + timeSeparator;
        }
    }
    dateNode.focus();
}


var chr = new Array();
//<!--NUMBERIC MAP-->
chr[48] = "\u0966";
chr[49] = "\u0967";
chr[50] = "\u0968";
chr[51] = "\u0969";
chr[52] = "\u096A";
chr[53] = "\u096B";
chr[54] = "\u096C";
chr[55] = "\u096D";
chr[56] = "\u096E";
chr[57] = "\u096F";

/*SHIFT NUMBERIC KEY*/
///not completed
chr[248] = "\u0923";
chr[249] = "\u091C\u094D\u091E";
chr[250] = "\u0908";
chr[251] = "\u0918";
chr[252] = "\u0926\u094D\u0927";
chr[253] = "\u091B";
chr[254] = "\u091F";
chr[255] = "\u0920";
chr[256] = "\u0921";
chr[257] = "\u0922";


//<!--FOR SMALL-->

chr[97] = "\u0906";
chr[98] = "\u094C";
chr[99] = "\u090B";
chr[100] = "\u0919\u094D\u0917";
chr[101] = "\u090E";
chr[102] = "\u0901";
chr[103] = "'\u0926\u094D\u0926'";
chr[104] = "\u091D";
chr[105] = "\u0915\u094D\u0937";
chr[106] = "\u094B";
chr[107] = "\u092B";
chr[108] = "\u0940";
chr[109] = "\u0921\u094D\u0921";
//chr[110] ="\u0926\u094D\u092F";
chr[111] = "\u0907";
chr[112] = "\u090F";
chr[113] = "\u0924\u094D\u0924";
chr[114] = "\u0926\u094D\u092C";
chr[115] = "\u0919\u094D\u0915";
chr[116] = "\u091F\u094D\u091F";
chr[117] = "\u090A";
chr[118] = "\u0950";
chr[119] = "\u0921\u094D\u0922";
chr[120] = "\u0939\u094D\u092F";
chr[121] = "\u0920\u094D\u0920";
chr[122] = "\u0915\u094D\u0915";



//<!--FOR CAPITAL-->

chr[65] = "\u092c";
chr[66] = "\u0926";
chr[67] = "\u0905";
chr[68] = "\u092E";
chr[69] = "\u092D";
chr[70] = "\u093E";
chr[71] = "\u0928";
chr[72] = "\u091C";
chr[73] = "\u0937";
chr[74] = "\u0935";
chr[75] = "\u092A";
chr[76] = "\u093F";
chr[77] = "\u0903";
chr[78] = "\u0932";
chr[79] = "\u092F";
chr[80] = "\u0909";
chr[81] = "\u0924\u094D\u0930";
chr[82] = "\u091A";
chr[83] = "\u0915";
chr[84] = "\u0924";
chr[85] = "\u0917";
chr[86] = "\u0916";
chr[87] = "\u0927";
chr[88] = "\u0939";
chr[89] = "\u0925";
chr[90] = "\u0936";

/*SPECIAL KEY (EXELUCDABLE CHARACTER)*/

chr[16] = "";
chr[17] = "";
chr[18] = "";
chr[20] = "";
chr[8] = "";
chr[9] = "";
chr[27] = "";
chr[93] = "";
chr[37] = "";
chr[38] = "";
chr[39] = "";
chr[40] = "";
chr[33] = "";
chr[34] = "";
chr[35] = "";
chr[36] = "";
chr[46] = "";
chr[44] = "";
chr[45] = "";
chr[0] = "";
chr[91] = "";


/*SPACE BAR KEY*/
chr[32] = " ";

/*- + \ ~ [ ] sign*/
chr[173] = "\u0914";
chr[61] = " ";
chr[220] = "\u094D";
chr[192] = "\u091E";
chr[219] = "\u0930\u094D";
chr[221] = "\u0947";
chr[59] = "\u0938";
chr[222] = "\u0941";


chr[188] = "";
chr[190] = "\u0964";
chr[191] = "\u0930";

/*- + \ ~ [ ] sign with shift*/
chr[573] = "\u0913";
chr[461] = " ";
chr[620] = "\u0902";
chr[592] = "\u0965";
chr[619] = "\u0943";
chr[621] = "\u0948";
chr[459] = "\u091F\u094D\u0920";
chr[622] = "\u0942";


chr[588] = "\u0919";
chr[590] = "\u0936\u094D\u0930";
chr[591] = "";

//added later
chr[189] = "\u0975";
chr[186] = "\u0938";
chr[591] = "\u0938";
chr[188] = "\u0919";
chr[187] = "\u0971";
chr[110] = "\u002E";

function toNepali(e, obj) {
    var code = e.keyCode;

    if (chr[code] === undefined) {
        console.log('undefined');
        e.preventDefault();
    } else if (code === 8 || code === 9) {
        return true;
    } else if (chr[code] !== '') {
        var boxValue = obj.value;
        var boxValueLength = boxValue.length;

        if (e.shiftKey) {
            if (code > 47 && code < 58) {
                code = parseInt(code) + 200;
            }
            else if (code === 173 || code === 61 || code === 220 || code === 192 || code === 219 || code === 221 || code === 59 || code === 222 || code === 188 || code === 190 || code === 191) {
                code = parseInt(code) + 400;
            }
            else {
                code = parseInt(code) + 32;
            }
        }

        if (boxValueLength === 0) {
            obj.value = chr[code];
        } else {
            obj.value = obj.value + chr[code];
        }
    }
    console.log(code);
    e.preventDefault();
}

function nepaliIntOnly(obj, e) {
    if (keyRestrict(obj, e, '1234567890')) {
        toNepali(e, obj);
    } else {
        e.preventDefault();
    }
}

function nepaliFloatOnly(obj, e) {
    console.log(e.keyCode);
    if (e.keyCode === 110) {
        return true;
    }
    if (keyRestrict(obj, e, '1234567890.')) {
        toNepali(e, obj);
    } else {
        e.preventDefault();
    }
}


var num = new Array();
num[0] = "\u0966";
num[1] = "\u0967";
num[2] = "\u0968";
num[3] = "\u0969";
num[4] = "\u096A";
num[5] = "\u096B";
num[6] = "\u096C";
num[7] = "\u096D";
num[8] = "\u096E";
num[9] = "\u096F";

function engToNepNum(engNum) {
    var nepNum = "";
    for (var counter = 0; counter <= engNum.length - 1; counter++) {
        if (engNum[counter] >= 0 || engNum[counter] <= 9) {
            nepNum = nepNum + num[engNum[counter]];
        } else {
            nepNum = nepNum + engNum[counter];
        }
    }
    return nepNum;
}

function maskFiscalYear(e, obj) {
    if (obj.value.length === 2) {
        obj.value = obj.value + '/';
    }
    if (obj.value.length > 2 && obj.value[2] !== '/') {
        obj.value[2] = '/';
    }
}

function autoHashTime(timeNode, e) {
    if (timeNode === null && timeNode.value.length === 0)
        return;
    var unicode = e.keyCode ? e.keyCode : e.charCode;

    if (unicode !== 3) {
        if (timeNode.value.length === 2) {
            timeNode.value = timeNode.value + timeSeparator;
        }
    }
    timeNode.focus();
}

function autoHashAmPmTime(timeNode, e) {
    if (timeNode === null && timeNode.value.length === 0)
        return;
    var unicode = e.keyCode ? e.keyCode : e.charCode;

    if (unicode !== 7) {
        if (timeNode.value.length === 2) {
            timeNode.value = timeNode.value + timeSeparator;
        }
        if (timeNode.value.length === 5) {
            timeNode.value = timeNode.value + " ";
        }
        if (timeNode.value.length === 7) {
            timeNode.value = timeNode.value + "M";
        }
    }
    timeNode.focus();
}
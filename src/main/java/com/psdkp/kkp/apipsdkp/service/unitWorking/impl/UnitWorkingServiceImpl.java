package com.psdkp.kkp.apipsdkp.service.unitWorking.impl;

import com.psdkp.kkp.apipsdkp.domain.address.City;
import com.psdkp.kkp.apipsdkp.domain.unitWorking.TypeUnit;
import com.psdkp.kkp.apipsdkp.domain.unitWorking.UnitWorking;
import com.psdkp.kkp.apipsdkp.repository.address.CityDao;
import com.psdkp.kkp.apipsdkp.repository.unitWorking.TypeUnitDao;
import com.psdkp.kkp.apipsdkp.repository.unitWorking.UnitWorkingDao;
import com.psdkp.kkp.apipsdkp.service.unitWorking.UnitWorkingService;
import com.psdkp.kkp.apipsdkp.util.ResponMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class UnitWorkingServiceImpl implements UnitWorkingService {

    @Autowired
    private UnitWorkingDao unitWorkingDao;

    @Autowired
    private TypeUnitDao typeUnitDao;

    @Autowired
    private ResponMessage responMessage;

    @Autowired
    private CityDao cityDao;

    @Override
    public Object findAll(String name, Pageable pageable) {
        return responMessage.SUCCESS_GET(unitWorkingDao.findAllByName(name, pageable));
    }

    @Override
    public Object save(UnitWorking unitWorking) {
        if (unitWorking.getName().equals("") || unitWorking.getCode().equals("") || unitWorking.getEmail().equals("") || unitWorking.getPhone().equals("")) {
            return responMessage.BAD_REUQEST();
        } else {
            UnitWorking u1 = unitWorkingDao.findByCode(unitWorking.getCode());
            UnitWorking u2 = unitWorkingDao.findByName(unitWorking.getName());
            UnitWorking u3 = unitWorkingDao.findByPhone(unitWorking.getPhone());
            UnitWorking u4 = unitWorkingDao.findByFacsimile(unitWorking.getFacsimile());
            UnitWorking u5 = unitWorkingDao.findByEmail(unitWorking.getEmail());
            TypeUnit u9 = typeUnitDao.findId(unitWorking.getTypeUnit().getId());
            City u10 = cityDao.findId(unitWorking.getCity().getId());

            if (u9 == null) {
                return responMessage.NOT_FOUND("UNIT TYPE");
            } else if (u1 != null) {
                return responMessage.DUPLICATE("KODE");
            } else if (u2 != null) {
                return responMessage.DUPLICATE("NAME");
            } else if (u3 != null) {
                return responMessage.DUPLICATE("PHONE");
            } else if (!unitWorking.getFacsimile().equals("-")) {
                if (u4 != null) {
                    return responMessage.DUPLICATE("FACSIMILE");
                } else {
                    if (u5 != null) {
                        return responMessage.DUPLICATE("EMAIL");
                    } else if (u10 == null) {
                        return responMessage.NOT_FOUND("ID CITY");
                    } else {
                        unitWorkingDao.save(unitWorking);
                        return responMessage.SUCCESS_PROCESS_DATA();
                    }
                }
            } else if (u5 != null) {
                return responMessage.DUPLICATE("EMAIL");
            } else if (u10 == null) {
                return responMessage.NOT_FOUND("ID CITY");
            } else {
                unitWorkingDao.save(unitWorking);
                return responMessage.SUCCESS_PROCESS_DATA();
            }
        }
    }

    @Override
    public Object edit(UnitWorking unitWorking) {
        if (unitWorking.getId() == null || unitWorking.getCode().equals("") || unitWorking.getName().equals("") || unitWorking.getPhone().equals("") || unitWorking.getFacsimile().equals("") || unitWorking.getEmail().equals("") || unitWorking.getLatitude().equals("") || unitWorking.getLongitude().equals("")) {
            return responMessage.BAD_REUQEST();
        } else {
            UnitWorking unitId = unitWorkingDao.findId(unitWorking.getId());

            if (unitId != null) {

                TypeUnit uw0 = typeUnitDao.findId(unitWorking.getTypeUnit().getId());
                UnitWorking uw1 = unitWorkingDao.findByCode(unitWorking.getCode());
                UnitWorking uw2 = unitWorkingDao.findByName(unitWorking.getName());
                UnitWorking uw3 = unitWorkingDao.findByPhone(unitWorking.getPhone());
                UnitWorking uw4 = unitWorkingDao.findByFacsimile(unitWorking.getFacsimile());
                UnitWorking uw5 = unitWorkingDao.findByEmail(unitWorking.getEmail());
                City u10 = cityDao.findId(unitWorking.getCity().getId());

                if (uw0 == null) {
                    return responMessage.NOT_FOUND("UNIT TYPE");
                } else {
                    if (!unitId.getCode().equals(unitWorking.getCode())) {
                        if (uw1 != null) {
                            return responMessage.DUPLICATE("CODE");
                        } else {
                            if (!unitId.getName().equals(unitWorking.getName())) {
                                if (uw2 != null) {
                                    return responMessage.DUPLICATE("NAME");
                                } else {
                                    if (!unitId.getPhone().equals(unitWorking.getPhone())) {
                                        if (uw3 != null) {
                                            return responMessage.DUPLICATE("PHONE");
                                        } else {
                                            if (!unitWorking.getFacsimile().equals("-") && !unitId.getFacsimile().equals(unitWorking.getFacsimile())) {
                                                if (uw4 != null) {
                                                    return responMessage.DUPLICATE("FACSIMILE");
                                                } else {
                                                    if (!unitId.getEmail().equals(unitWorking.getEmail())) {
                                                        if (uw5 != null) {
                                                            return responMessage.DUPLICATE("EMAIL");
                                                        } else {
                                                            unitWorkingDao.save(unitWorking);
                                                            return responMessage.SUCCESS_PROCESS_DATA();
                                                        }
                                                    } else {
                                                        unitWorkingDao.save(unitWorking);
                                                        return responMessage.SUCCESS_PROCESS_DATA();
                                                    }
                                                }
                                            } else {
                                                if (!unitId.getEmail().equals(unitWorking.getEmail())) {
                                                    if (uw5 != null) {
                                                        return responMessage.DUPLICATE("EMAIL");
                                                    } else {
                                                        unitWorkingDao.save(unitWorking);
                                                        return responMessage.SUCCESS_PROCESS_DATA();
                                                    }
                                                } else {
                                                    unitWorkingDao.save(unitWorking);
                                                    return responMessage.SUCCESS_PROCESS_DATA();
                                                }
                                            }
                                        }
                                    } else {
                                        if (!unitWorking.getFacsimile().equals("-") && !unitId.getFacsimile().equals(unitWorking.getFacsimile())) {
                                            if (uw4 != null) {
                                                return responMessage.DUPLICATE("FACSIMILE");
                                            } else {
                                                if (!unitId.getEmail().equals(unitWorking.getEmail())) {
                                                    if (uw5 != null) {
                                                        return responMessage.DUPLICATE("EMAIL");
                                                    } else {
                                                        unitWorkingDao.save(unitWorking);
                                                        return responMessage.SUCCESS_PROCESS_DATA();
                                                    }
                                                } else {
                                                    unitWorkingDao.save(unitWorking);
                                                    return responMessage.SUCCESS_PROCESS_DATA();
                                                }
                                            }
                                        } else {
                                            if (!unitId.getEmail().equals(unitWorking.getEmail())) {
                                                if (uw5 != null) {
                                                    return responMessage.DUPLICATE("EMAIL");
                                                } else {
                                                    unitWorkingDao.save(unitWorking);
                                                    return responMessage.SUCCESS_PROCESS_DATA();
                                                }
                                            } else {
                                                unitWorkingDao.save(unitWorking);
                                                return responMessage.SUCCESS_PROCESS_DATA();
                                            }
                                        }
                                    }
                                }
                            } else {
                                if (!unitId.getPhone().equals(unitWorking.getPhone())) {
                                    if (uw3 != null) {
                                        return responMessage.DUPLICATE("PHONE");
                                    } else {
                                        if (!unitWorking.getFacsimile().equals("-") && !unitId.getFacsimile().equals(unitWorking.getFacsimile())) {
                                            if (uw4 != null) {
                                                return responMessage.DUPLICATE("FACSIMILE");
                                            } else {
                                                if (!unitId.getEmail().equals(unitWorking.getEmail())) {
                                                    if (uw5 != null) {
                                                        return responMessage.DUPLICATE("EMAIL");
                                                    } else {
                                                        unitWorkingDao.save(unitWorking);
                                                        return responMessage.SUCCESS_PROCESS_DATA();
                                                    }
                                                } else {
                                                    unitWorkingDao.save(unitWorking);
                                                    return responMessage.SUCCESS_PROCESS_DATA();
                                                }
                                            }
                                        } else {
                                            if (!unitId.getEmail().equals(unitWorking.getEmail())) {
                                                if (uw5 != null) {
                                                    return responMessage.DUPLICATE("EMAIL");
                                                } else {
                                                    unitWorkingDao.save(unitWorking);
                                                    return responMessage.SUCCESS_PROCESS_DATA();
                                                }
                                            } else {
                                                unitWorkingDao.save(unitWorking);
                                                return responMessage.SUCCESS_PROCESS_DATA();

                                            }
                                        }
                                    }
                                } else {
                                    if (!unitWorking.getFacsimile().equals("-") && !unitId.getFacsimile().equals(unitWorking.getFacsimile())) {
                                        if (uw4 != null) {
                                            return responMessage.DUPLICATE("FACSIMILE");
                                        } else {
                                            if (!unitId.getEmail().equals(unitWorking.getEmail())) {
                                                if (uw5 != null) {
                                                    return responMessage.DUPLICATE("EMAIL");
                                                } else {
                                                    unitWorkingDao.save(unitWorking);
                                                    return responMessage.SUCCESS_PROCESS_DATA();
                                                }
                                            } else {
                                                unitWorkingDao.save(unitWorking);
                                                return responMessage.SUCCESS_PROCESS_DATA();
                                            }
                                        }
                                    } else {
                                        if (!unitId.getEmail().equals(unitWorking.getEmail())) {
                                            if (uw5 != null) {
                                                return responMessage.DUPLICATE("EMAIL");
                                            } else {
                                                unitWorkingDao.save(unitWorking);
                                                return responMessage.SUCCESS_PROCESS_DATA();
                                            }
                                        } else {
                                            unitWorkingDao.save(unitWorking);
                                            return responMessage.SUCCESS_PROCESS_DATA();
                                        }
                                    }
                                }
                            }
                        }
                    } else {
                        if (!unitId.getName().equals(unitWorking.getName())) {
                            if (uw2.getName() != null) {
                                return responMessage.DUPLICATE("NAME");
                            } else {
                                if (!unitId.getPhone().equals(unitWorking.getPhone())) {
                                    if (uw3 != null) {
                                        return responMessage.DUPLICATE("PHONE");
                                    } else {
                                        if (!unitWorking.getFacsimile().equals("-") && !unitId.getFacsimile().equals(unitWorking.getFacsimile())) {
                                            if (uw4 != null) {
                                                return responMessage.DUPLICATE("FACSIMILE");
                                            } else {
                                                if (!unitId.getEmail().equals(unitWorking.getEmail())) {
                                                    if (uw5 != null) {
                                                        return responMessage.DUPLICATE("EMAIL");
                                                    } else {
                                                        unitWorkingDao.save(unitWorking);
                                                        return responMessage.SUCCESS_PROCESS_DATA();
                                                    }
                                                } else {
                                                    unitWorkingDao.save(unitWorking);
                                                    return responMessage.SUCCESS_PROCESS_DATA();
                                                }
                                            }
                                        } else {
                                            if (!unitId.getEmail().equals(unitWorking.getEmail())) {
                                                if (uw5 != null) {
                                                    return responMessage.DUPLICATE("EMAIL");
                                                } else {
                                                    unitWorkingDao.save(unitWorking);
                                                    return responMessage.SUCCESS_PROCESS_DATA();
                                                }
                                            } else {
                                                unitWorkingDao.save(unitWorking);
                                                return responMessage.SUCCESS_PROCESS_DATA();
                                            }
                                        }
                                    }
                                } else {
                                    if (!unitWorking.getFacsimile().equals("-") && !unitId.getFacsimile().equals(unitWorking.getFacsimile())) {
                                        if (uw4 != null) {
                                            return responMessage.DUPLICATE("FACSIMILE");
                                        } else {
                                            if (!unitId.getEmail().equals(unitWorking.getEmail())) {
                                                if (uw5 != null) {
                                                    return responMessage.DUPLICATE("EMAIL");
                                                } else {
                                                    unitWorkingDao.save(unitWorking);
                                                    return responMessage.SUCCESS_PROCESS_DATA();
                                                }
                                            } else {
                                                unitWorkingDao.save(unitWorking);
                                                return responMessage.SUCCESS_PROCESS_DATA();
                                            }
                                        }
                                    } else {
                                        if (!unitId.getEmail().equals(unitWorking.getEmail())) {
                                            if (uw5 != null) {
                                                return responMessage.DUPLICATE("EMAIL");
                                            } else {
                                                unitWorkingDao.save(unitWorking);
                                                return responMessage.SUCCESS_PROCESS_DATA();
                                            }
                                        } else {
                                            unitWorkingDao.save(unitWorking);
                                            return responMessage.SUCCESS_PROCESS_DATA();
                                        }
                                    }
                                }
                            }
                        } else {
                            if (!unitId.getPhone().equals(unitWorking.getPhone())) {
                                if (uw3 != null) {
                                    return responMessage.DUPLICATE("PHONE");
                                } else {
                                    if (!unitWorking.getFacsimile().equals("-") && !unitId.getFacsimile().equals(unitWorking.getFacsimile())) {
                                        if (uw4 != null) {
                                            return responMessage.DUPLICATE("FACSIMILE");
                                        } else {
                                            if (!unitId.getEmail().equals(unitWorking.getEmail())) {
                                                if (uw5 != null) {
                                                    return responMessage.DUPLICATE("EMAIL");
                                                } else {
                                                    unitWorkingDao.save(unitWorking);
                                                    return responMessage.SUCCESS_PROCESS_DATA();
                                                }
                                            } else {
                                                unitWorkingDao.save(unitWorking);
                                                return responMessage.SUCCESS_PROCESS_DATA();
                                            }
                                        }
                                    } else {
                                        if (!unitId.getEmail().equals(unitWorking.getEmail())) {
                                            if (uw5 != null) {
                                                return responMessage.DUPLICATE("EMAIL");
                                            } else {
                                                unitWorkingDao.save(unitWorking);
                                                return responMessage.SUCCESS_PROCESS_DATA();
                                            }
                                        } else {
                                            unitWorkingDao.save(unitWorking);
                                            return responMessage.SUCCESS_PROCESS_DATA();
                                        }
                                    }
                                }
                            } else {
                                if (!unitWorking.getFacsimile().equals("-") && !unitId.getFacsimile().equals(unitWorking.getFacsimile())) {
                                    if (uw4 != null) {
                                        return responMessage.DUPLICATE("FACSIMILE");
                                    } else {
                                        if (!unitId.getEmail().equals(unitWorking.getEmail())) {
                                            if (uw5 != null) {
                                                return responMessage.DUPLICATE("EMAIL");
                                            } else {
                                                unitWorkingDao.save(unitWorking);
                                                return responMessage.SUCCESS_PROCESS_DATA();
                                            }
                                        } else {
                                            unitWorkingDao.save(unitWorking);
                                            return responMessage.SUCCESS_PROCESS_DATA();
                                        }
                                    }
                                } else {
                                    if (!unitId.getEmail().equals(unitWorking.getEmail())) {
                                        if (uw5 != null) {
                                            return responMessage.DUPLICATE("EMAIL");
                                        } else {
                                            unitWorkingDao.save(unitWorking);
                                            return responMessage.SUCCESS_PROCESS_DATA();
                                        }
                                    } else {
                                        unitWorkingDao.save(unitWorking);
                                        return responMessage.SUCCESS_PROCESS_DATA();
                                    }
                                }
                            }
                        }
                    }
                }
            } else {
                return responMessage.NOT_FOUND("ID");
            }
        }
    }

    @Override
    public Object del(Integer id) {
        if (id == null) {
            return responMessage.BAD_REUQEST();
        } else {
            UnitWorking unitWorking = unitWorkingDao.findId(id);
            if (unitWorking != null) {
                unitWorkingDao.deleteById(id);
                return responMessage.SUCCESS_PROCESS_DATA();
            } else {
                return responMessage.NOT_FOUND("ID");
            }
        }
    }

    @Override
    public Object findById(Integer id) {
        UnitWorking unitWorking = unitWorkingDao.findId(id);
        if (unitWorking != null) {
            return responMessage.SUCCESS_GET(unitWorkingDao.findById(id));
        } else {
            return responMessage.NOT_FOUND("ID");
        }
    }

    @Override
    public Object findByTypeUnit(Integer id, Pageable pageable) {
        if (id != null) {
            Page<UnitWorking> unitWorking = unitWorkingDao.findByTypeUnit(id, pageable);
            if (unitWorking != null) {
                return responMessage.SUCCESS_GET(unitWorking);
            } else {
                return responMessage.NOT_FOUND("ID TYPE");
            }
        } else {
            return responMessage.BAD_REUQEST();
        }
    }
}

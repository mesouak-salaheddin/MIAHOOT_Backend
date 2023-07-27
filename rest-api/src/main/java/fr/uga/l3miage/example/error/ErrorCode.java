package fr.uga.l3miage.example.error;


/**
 * Représente les codes d'erreur possibles pour une errorResponse
 * Va permettre d'être le discriminant lors de la deserialization des erreurs
 */
public enum ErrorCode {
    TEST_INT_IS_ZERO_ERROR,
    TOO_MANY_TRUE_ANSWERS,
    IS_NOT_TEST_ERROR,
    FIREBASEID_NOT_FOUND,
    TEST_IS_NOT_FOUND,
    DESCRIPTION_ALREADY_USE_ERROR,
    FIREBASE_ID_ALREADY_USE_ERROR,
    TEST_ENTITY_NOT_DELETED_ERROR,
    //MIAHOOT
    MIAHOOT_IS_NOT_FOUND,
    QUESTION_IS_NOT_FOUND
}
